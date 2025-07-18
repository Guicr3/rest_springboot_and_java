package com.guicr3.project_java_springboot.controllers;

import com.guicr3.project_java_springboot.data.dto.PersonDTO;
import com.guicr3.project_java_springboot.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;

        @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        public PersonDTO findById(@PathVariable("id") Long id){
            return service.findByID(id);
        }

        @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        public ResponseEntity<List<PersonDTO>> findAll(){
            return ResponseEntity.ok().body(service.findAll());
        }

        @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person){
            return ResponseEntity.ok().body(service.create(person));
        }

        @PutMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO updatedPerson, @PathVariable Long id){
            updatedPerson.setId(id);
            return ResponseEntity.ok().body(service.update(updatedPerson));
        }

        @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        public ResponseEntity<Void> delete(@PathVariable("id") Long id){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

}

