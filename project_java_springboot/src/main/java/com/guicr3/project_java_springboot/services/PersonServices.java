package com.guicr3.project_java_springboot.services;

import com.guicr3.project_java_springboot.data.dto.PersonDTO;
import com.guicr3.project_java_springboot.exception.ResourceNotFoundException;
import static com.guicr3.project_java_springboot.mapper.ObjectMapper.parseObject;
import static com.guicr3.project_java_springboot.mapper.ObjectMapper.parseListObjects;

import com.guicr3.project_java_springboot.model.Person;
import com.guicr3.project_java_springboot.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository repository;

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public List<PersonDTO> findAll(){
        logger.info("Finding all users");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findByID(Long id){
        logger.info("Finding one Person");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records founds for this id"));
        return parseObject(person, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO newPerson){
        logger.info("Creating a person");
        Person person = parseObject(newPerson, Person.class);
        return parseObject(repository.save(person), PersonDTO.class);
    }

    public void update(PersonDTO personDTO){
        logger.info("Updating a person");
        Person person = repository.findById(personDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        updateData(person, personDTO);
        parseObject(repository.save(person), PersonDTO.class);
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
}
