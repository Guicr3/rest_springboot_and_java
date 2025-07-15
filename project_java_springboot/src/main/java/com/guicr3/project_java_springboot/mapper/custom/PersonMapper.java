package com.guicr3.project_java_springboot.mapper.custom;

import com.guicr3.project_java_springboot.data.dto.v2.PersonDTOV2;
import com.guicr3.project_java_springboot.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person){
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setBirthDay(new Date());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        return dto;
    }

    public Person convertDTOtoEntity(PersonDTOV2 dto){
        Person person = new Person();
        person.setId(dto.getId());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        //person.setBirthDay(new Date());
        person.setAddress(dto.getAddress());
        person.setGender(dto.getGender());
        return person;
    }
}
