package com.guicr3.project_java_springboot.controllers;

import com.guicr3.project_java_springboot.data.dto.v1.PersonDTO;
import com.guicr3.project_java_springboot.data.dto.v2.PersonDTOV2;
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
        private PersonDTO findById(@PathVariable("id") Long id){
            return service.findByID(id);
        }

        @GetMapping
        private ResponseEntity<List<PersonDTO>> findAll(){
            return ResponseEntity.ok().body(service.findAll());
        }

        @PostMapping
        private ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person){
            return ResponseEntity.ok().body(service.create(person));
        }

        @PostMapping(value="/v2")
        private ResponseEntity<PersonDTOV2> create(@RequestBody PersonDTOV2 person){
            return ResponseEntity.ok().body(service.createV2(person));
        }

        @PutMapping(value="/{id}")
        private ResponseEntity<Void> update(@RequestBody PersonDTO updatedPerson, @PathVariable Long id){
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

