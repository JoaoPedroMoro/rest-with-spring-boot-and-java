package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Finding one Person!");

        // Vamos mockar - Mock é uma estrutura que suporta e sustenta a estrutura enquanto as coisas não estão sólidas
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("João Pedro");
        person.setLastName("Moro");
        person.setAddress("São José do Rio Preto - São Paulo - Brasil");
        person.setGender("Male");

        return person;

    }

}
