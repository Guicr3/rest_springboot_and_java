package com.guicr3.project_java_springboot.services;

import com.guicr3.project_java_springboot.controllers.BookController;
import com.guicr3.project_java_springboot.data.dto.BookDTO;
import com.guicr3.project_java_springboot.exception.RequiredObjectIsNullException;
import com.guicr3.project_java_springboot.exception.ResourceNotFoundException;
import com.guicr3.project_java_springboot.model.Book;
import com.guicr3.project_java_springboot.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.guicr3.project_java_springboot.mapper.ObjectMapper.parseListObjects;
import static com.guicr3.project_java_springboot.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    @Autowired
    private BookRepository repository;

    private final Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    public List<BookDTO> findAll() {
        logger.info("Finding all Book!");
        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }

    public BookDTO findById(Long id) {
        logger.info("Finding one Book!");
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        BookDTO bookDTO = parseObject(book, BookDTO.class);
        addHateoasLinks(bookDTO);
        return bookDTO;
    }

    public BookDTO create(BookDTO bookDTO) {
        if (bookDTO == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one Book!");
        Book book = parseObject(bookDTO, Book.class);
        BookDTO dto = parseObject(repository.save(book), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO bookDTO) {
        if (bookDTO == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Book!");
        Book book = repository.findById(bookDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        updateBook(book, bookDTO);
        BookDTO dto = parseObject(repository.save(book), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting one Book!");
        var book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records founds for this id"));
        repository.delete(book);
    }

    public void updateBook(Book book, BookDTO bookDTO) {
        book.setAuthor(bookDTO.getAuthor());
        book.setLaunchDate(bookDTO.getLaunchDate());
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getTitle());
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto, dto.getId())).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}