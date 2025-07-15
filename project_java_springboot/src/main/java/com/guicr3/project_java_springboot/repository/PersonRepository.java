package com.guicr3.project_java_springboot.repository;

import com.guicr3.project_java_springboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
