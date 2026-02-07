package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.controllers;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.model.Person;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;
    // private PersonServices service = new PersonServices();

    @RequestMapping(value = "/{id}",
        method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person findById(@PathVariable("id") Long id) {

        return service.findById(id);

    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Person> findAll() {

        return service.findAll();

    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, // Se não especificarmos, quando o swagger for gerar a documentação da API, ele vai ser perder
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person) {

        return service.create(person);

    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, // Se não especificarmos, quando o swagger for gerar a documentação da API, ele vai ser perder
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@RequestBody Person person) {

        return service.update(person);

    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
