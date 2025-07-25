package com.guicr3.project_java_springboot.unitTests.services;

import com.guicr3.project_java_springboot.controllers.PersonController;
import com.guicr3.project_java_springboot.data.dto.PersonDTO;
import com.guicr3.project_java_springboot.exception.RequiredObjectIsNullException;
import com.guicr3.project_java_springboot.exception.ResourceNotFoundException;
import static com.guicr3.project_java_springboot.mapper.ObjectMapper.parseObject;
import static com.guicr3.project_java_springboot.mapper.ObjectMapper.parseListObjects;

import com.guicr3.project_java_springboot.model.Person;
import com.guicr3.project_java_springboot.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository repository;

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public List<PersonDTO> findAll(){
        logger.info("Finding all users");
        var people = parseListObjects(repository.findAll(), PersonDTO.class);
        people.forEach(this::addHateoasLinks);
        return people;
    }

    public PersonDTO findByID(Long id){
        logger.info("Finding one Person");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records founds for this id"));
        PersonDTO personDTO = parseObject(person, PersonDTO.class);
        addHateoasLinks(personDTO);
        return personDTO;
    }

    public PersonDTO create(PersonDTO personDTO){
        if (personDTO == null)  throw new RequiredObjectIsNullException();

        logger.info("Creating a person");
        Person person = parseObject(personDTO, Person.class);
        PersonDTO dto = parseObject(repository.save(person), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO update(PersonDTO personDTO){
        if (personDTO == null)  throw new RequiredObjectIsNullException();

        logger.info("Updating a person");
        Person person = repository.findById(personDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        updateData(person, personDTO);
        PersonDTO dto = parseObject(repository.save(person), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting a person");
        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records founds for this id"));
        repository.delete(person);
    }

    public void updateData(Person person, PersonDTO personDTO){
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setAddress(personDTO.getAddress());
        person.setGender(personDTO.getGender());
    }

    private void addHateoasLinks(PersonDTO dto){
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto, dto.getId())).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
