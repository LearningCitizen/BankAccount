package com.jhippolyte.bankaccount.service;

import com.jhippolyte.bankaccount.model.Person;
import com.jhippolyte.bankaccount.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    List<Person> getAllPersons(){
        return personRepository.findAll();
    }
}
