package com.guicr3.project_java_springboot.repository;

import com.guicr3.project_java_springboot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
