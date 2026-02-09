package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.controllers;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.model.Person;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;
    // private PersonServices service = new PersonServices();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable("id") Long id) {

        return service.findById(id);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {

        return service.findAll();

    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE, // Se não especificarmos, quando o swagger for gerar a documentação da API, ele vai ser perder
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person) {

        return service.create(person);

    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE, // Se não especificarmos, quando o swagger for gerar a documentação da API, ele vai ser perder
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@RequestBody Person person) {

        return service.update(person);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
