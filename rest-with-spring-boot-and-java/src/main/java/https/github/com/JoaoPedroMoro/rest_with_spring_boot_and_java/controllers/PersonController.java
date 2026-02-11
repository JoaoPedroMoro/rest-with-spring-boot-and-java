package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.controllers;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.data.dto.PersonDTO;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;
    // private PersonServices service = new PersonServices();

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, // Se não especificarmos, quando o swagger for gerar a documentação da API, ele vai ser perder
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PersonDTO create(@RequestBody PersonDTO person) {

        return service.create(person);

    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, // Se não especificarmos, quando o swagger for gerar a documentação da API, ele vai ser perder
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PersonDTO update(@RequestBody PersonDTO person) {

        return service.update(person);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
