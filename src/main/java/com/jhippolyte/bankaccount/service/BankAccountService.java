package com.jhippolyte.bankaccount.service;

import com.jhippolyte.bankaccount.exception.BankAccountNotFoundException;
import com.jhippolyte.bankaccount.model.BankAccount;
import com.jhippolyte.bankaccount.repository.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    Logger logger = LoggerFactory.getLogger(BankAccountService.class);

    public double getBalance(String accountNumber) throws BankAccountNotFoundException {
        logger.info("Get Account with account number");
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new BankAccountNotFoundException("No bank account found for account number  : " + accountNumber);
        }
        logger.info("Get Balance of the account");
        return account.getBalance();
    }
}
