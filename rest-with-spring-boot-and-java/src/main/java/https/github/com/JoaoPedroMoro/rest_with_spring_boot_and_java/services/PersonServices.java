package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all People!");

        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person) {
        logger.info("Creating one person!");

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one person!");

        return person;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("First Name " + i);
        person.setLastName("Last Name " + i);
        person.setAddress("Address " + i);
        person.setGender("Gender " + i);

        return person;
    }

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
    public void delete(String id){
        logger.info("Deleting one Person!");
    }

}
