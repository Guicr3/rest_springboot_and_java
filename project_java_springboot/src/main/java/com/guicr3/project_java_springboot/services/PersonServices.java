package com.guicr3.project_java_springboot.services;

import com.guicr3.project_java_springboot.exception.ResourceNotFoundException;
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

    public List<Person> findAll(){
        logger.info("Finding all users");
        return repository.findAll();
    }

    public Person findByID(Long id){
        logger.info("Finding one Person");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records founds for this id"));
    }

    public Person create(Person person){
        logger.info("Creating a person");
        return repository.save(person);
    }

    public void update(Person person){
        logger.info("Updating a person");
        Person newPerson = findByID(person.getId());
        updateData(newPerson, person);
        repository.save(newPerson);
    }

    public void delete(Long id){
        logger.info("Deleting a person");
        Person person = findByID(id);
        repository.delete(person);
    }

    public void updateData(Person newObj, Person obj){
        newObj.setName(obj.getName());
        newObj.setLastName(obj.getLastName());
        newObj.setAddress(obj.getAddress());
        newObj.setGender(obj.getGender());
    }
}
