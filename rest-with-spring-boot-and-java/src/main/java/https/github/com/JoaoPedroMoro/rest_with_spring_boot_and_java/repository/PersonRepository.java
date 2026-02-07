package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.repository;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

// Só com essa implementação é possível fazer um CRUD básico
public interface PersonRepository extends JpaRepository<Person, Long> {
}
