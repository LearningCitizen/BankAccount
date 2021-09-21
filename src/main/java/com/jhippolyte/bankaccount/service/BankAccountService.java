package com.jhippolyte.bankaccount.service;

import com.jhippolyte.bankaccount.model.BankAccount;
import com.jhippolyte.bankaccount.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    public double getBalance(String accountNumber){
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        return account.getBalance();
    }
}
