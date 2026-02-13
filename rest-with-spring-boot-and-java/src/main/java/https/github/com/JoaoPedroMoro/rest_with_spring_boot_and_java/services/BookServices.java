package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.controllers.BookController;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.data.dto.BookDTO;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.exception.RequiredObjectIsNullException;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.model.Book;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObjects;
import static https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    
    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());
    
    @Autowired
    BookRepository repository;
    
    public List<BookDTO> findAll() {
        logger.info("Finding all Books!");
        
        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }
    
    public BookDTO findById(Long id) {
        logger.info("Finding one Book!");
        
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }
    
    public BookDTO create(BookDTO book) {
        
        if (book == null) throw new RequiredObjectIsNullException();
        
        logger.info("Creating one Book!");
        var entity = parseObject(book, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
        
    }
    
    public BookDTO update (BookDTO book) {
        
        if (book == null) throw new RequiredObjectIsNullException();
        
        logger.info("Updating one book!");
        
        Book entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        
        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        entity.setLaunchDate(book.getLaunchDate());
        
        var dto = parseObject(repository.save(entity), BookDTO.class);
        
        addHateoasLinks(dto);
        
        return dto;
        
    }
    
    public void delete(Long id) {
        
        logger.info("Deleting one Book!");
        
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        
        repository.delete(entity);
        
    }
    
    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));

    }
    
}
