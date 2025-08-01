package com.guicr3.project_java_springboot.services;

import com.guicr3.project_java_springboot.data.dto.PersonDTO;
import com.guicr3.project_java_springboot.exception.RequiredObjectIsNullException;
import com.guicr3.project_java_springboot.model.Person;
import com.guicr3.project_java_springboot.repository.PersonRepository;
import com.guicr3.project_java_springboot.unitTests.mapper.mocks.MockPerson;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices services;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByID() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        var result = services.findByID(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("GET"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("GET"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("POST"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("PUT"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("DELETE"));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void create() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = services.create(dto);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("GET"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("GET"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("POST"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("PUT"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("DELETE"));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
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

        var result = services.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("GET"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("GET"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("POST"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("PUT"));

        result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("DELETE"));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        services.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    services.create(null);
                });
        String expectedMessage = "Not Allowed to persist a null object! ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    services.update(null);
                });
        String expectedMessage = "Not Allowed to persist a null object! ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> people = services.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getLinks());

        personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("GET"));

        personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("GET"));

        personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("POST"));

        personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("PUT"));

        personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("DELETE"));

        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getId());
        assertNotNull(personFour.getLinks());

        personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/person/v1/4")
                && link.getType().equals("GET"));

        personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("GET"));

        personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("POST"));

        personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("PUT"));

        personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/person/v1/4")
                && link.getType().equals("DELETE"));

        assertEquals("Address Test4", personFour.getAddress());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getId());
        assertNotNull(personSeven.getLinks());

        personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/person/v1/7")
                && link.getType().equals("GET"));

        personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("GET"));

        personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("POST"));

        personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/person/v1")
                && link.getType().equals("PUT"));

        personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/person/v1/7")
                && link.getType().equals("DELETE"));

        assertEquals("Address Test7", personSeven.getAddress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());

    }
}