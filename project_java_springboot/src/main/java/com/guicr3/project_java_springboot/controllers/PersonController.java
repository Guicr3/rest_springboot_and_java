package com.guicr3.project_java_springboot.controllers;

import com.guicr3.project_java_springboot.controllers.docs.PersonControllerDocs;
import com.guicr3.project_java_springboot.data.dto.PersonDTO;
import com.guicr3.project_java_springboot.services.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonServices service;

        @CrossOrigin(origins = "http://localhost:8080")
        @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        @Override
        public PersonDTO findById(@PathVariable("id") Long id){
            return service.findByID(id);
        }

        @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        @Override
        public ResponseEntity<List<PersonDTO>> findAll(){
            return ResponseEntity.ok().body(service.findAll());
        }

        @CrossOrigin(origins = "http://localhost:8080")
        @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        @Override
        public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person){
            return ResponseEntity.ok().body(service.create(person));
        }

        @CrossOrigin(origins = "http://localhost:8080")
        @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        @Override
        public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO updatedPerson, @PathVariable Long id){
            updatedPerson.setId(id);
            return ResponseEntity.ok().body(service.update(updatedPerson));
        }

        @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        @Override
        public ResponseEntity<Void> delete(@PathVariable("id") Long id){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

}

