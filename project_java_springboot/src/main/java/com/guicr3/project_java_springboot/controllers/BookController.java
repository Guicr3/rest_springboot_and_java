package com.guicr3.project_java_springboot.controllers;

import com.guicr3.project_java_springboot.controllers.docs.BookControllerDocs;
import com.guicr3.project_java_springboot.data.dto.BookDTO;
import com.guicr3.project_java_springboot.services.BookServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController implements BookControllerDocs {

    @Autowired
    private BookServices service;

        @CrossOrigin(origins = "http://localhost:8080")
        @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE})
        @Override
        public BookDTO findById(@PathVariable("id") Long id) {
            return service.findById(id);
        }

        @CrossOrigin(origins = "http://localhost:8080")
        @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE})
        @Override
        public ResponseEntity<List<BookDTO>> findAll() {
            return ResponseEntity.ok().body(service.findAll());
        }

        @CrossOrigin(origins = "http://localhost:8080")
        @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        @Override
        public ResponseEntity<BookDTO> create(@RequestBody BookDTO book) {
            return ResponseEntity.ok().body(service.create(book));
        }

        @CrossOrigin(origins = "http://localhost:8080")
        @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        @Override
        public ResponseEntity<BookDTO> update(@RequestBody BookDTO updatedBook, @PathVariable Long id) {
            updatedBook.setId(id);
            return ResponseEntity.ok().body(service.update(updatedBook));
        }

        @DeleteMapping(value = "/{id}")
        @Override
        public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
}

