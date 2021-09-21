package com.jhippolyte.bankaccount.service;

import com.jhippolyte.bankaccount.dto.BankAccountDto;
import com.jhippolyte.bankaccount.dto.PersonDto;
import com.jhippolyte.bankaccount.model.BankAccount;
import com.jhippolyte.bankaccount.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapService {

    @Autowired
    ModelMapper modelMapper;

    public PersonDto mapToDto(Person person){
        return modelMapper.map(person, PersonDto.class);
    }

    public BankAccountDto mapToDto(BankAccount bankAccount){
        return modelMapper.map(bankAccount, BankAccountDto.class);
    }
}
