package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.data.dto.PersonDTO;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.model.Person;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.repository.PersonRepository;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.unitetests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Define que a instância da classe de teste será única para todos os testes
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

// Integra o Mockito com o JUnit 5
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    // Classe auxiliar para gerar objetos mockados de Person
    MockPerson input;

    // Cria uma instância real de PersonServices
    // e injeta automaticamente os mocks declarados com @Mock
    @InjectMocks
    private PersonServices service;

    // Cria um mock do PersonRepository
    // Ou seja, não acessa banco real
    @Mock
    PersonRepository repository;

    // Executado antes de cada teste
    @BeforeEach
    void setUp() {
        // Inicializa a classe que gera objetos mock
        input = new MockPerson();

        // Inicializa os mocks manualmente (não seria necessário
        // usando @ExtendWith(MockitoExtension.class),
        // mas não causa erro)
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {

        // Cria um objeto Person mockado
        Person person = input.mockEntity(1);

        // Define o ID manualmente
        person.setId(1L);

        // Define o comportamento do mock:
        // Quando repository.findById(1L) for chamado,
        // retornar Optional contendo o person criado acima
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        // Executa o método real do service
        var result = service.findById(1L);

        // Verifica se o resultado não é nulo
        assertNotNull(result);

        // Verifica se o ID foi retornado corretamente
        assertNotNull(result.getId());

        // Verifica se os links HATEOAS foram adicionados
        assertNotNull(result.getLinks());

        // Verifica se existe o link "self"
        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                )
        );

        // Verifica se existe o link "findAll"
        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        // Verifica se existe o link "create"
        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        // Verifica se existe o link "update"
        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        // Verifica se existe o link "delete"
        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        // Verifica se os dados básicos foram corretamente mapeados
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void create() {

        Person person = input.mockEntity(1);

        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());

    }

    @Test
    void update() {

        Person person = input.mockEntity(1);

        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks()
                .stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());

    }

    @Test
    void delete() {

        Person person = input.mockEntity(1);

        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);

    }

    @Test
    void findAll() {
    }
}
