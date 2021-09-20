package com.jhippolyte.bankaccount.repository;

import com.jhippolyte.bankaccount.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
