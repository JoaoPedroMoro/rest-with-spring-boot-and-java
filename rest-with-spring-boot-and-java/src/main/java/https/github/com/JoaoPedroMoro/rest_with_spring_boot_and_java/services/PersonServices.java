package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.controllers.PersonController;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.data.dto.PersonDTO;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import static https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObjects;
import static https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.model.Person;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all People!");

//        return repository.findAll(); // Isso aqui retorna uma lista de entidades, quero retornar uma lista de DTO
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");

        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        var dto = parseObject(entity, PersonDTO.class);

        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel().withType("GET"));

        return dto;
    }

    public PersonDTO create(PersonDTO person) {

        logger.info("Creating one Person!");
        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one person!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        repository.delete(entity);
    }

}
