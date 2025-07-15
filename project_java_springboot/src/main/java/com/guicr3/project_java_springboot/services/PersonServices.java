package com.guicr3.project_java_springboot.services;

import com.guicr3.project_java_springboot.exception.ResourceNotFoundException;
import com.guicr3.project_java_springboot.model.Person;
import com.guicr3.project_java_springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository repository;

    //private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person findByID(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records founds for this id"));
    }

    public Person create(Person person){
        return repository.save(person);
    }

    public void update(Person person){
        Person newPerson = findByID(person.getId());
        updateData(newPerson, person);
        repository.save(newPerson);
    }

    public void delete(Long id){
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
