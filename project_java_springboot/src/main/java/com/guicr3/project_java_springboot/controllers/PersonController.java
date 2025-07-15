package com.guicr3.project_java_springboot.controllers;

import com.guicr3.project_java_springboot.model.Person;
import com.guicr3.project_java_springboot.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/person")
public class PersonController {

    @Autowired
    private PersonServices service;

        @GetMapping(value="/{id}")
        private Person findById(@PathVariable("id") Long id){
            return service.findByID(id);
        }

        @GetMapping
        private ResponseEntity<List<Person>> findAll(){
            return ResponseEntity.ok().body(service.findAll());
        }

        @PostMapping
        private ResponseEntity<Person> create(@RequestBody Person person){
            return ResponseEntity.ok().body(service.create(person));
        }

        @PutMapping(value="/{id}")
        private ResponseEntity<Void> update(@RequestBody Person updatedPerson, @PathVariable Long id){
            updatedPerson.setId(id);
            service.update(updatedPerson);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping(value="/{id}")
        private ResponseEntity<Void> delete(@PathVariable("id") Long id){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

}

