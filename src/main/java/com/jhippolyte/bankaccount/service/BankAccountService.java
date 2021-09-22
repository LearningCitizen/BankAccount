package com.jhippolyte.bankaccount.service;

import com.jhippolyte.bankaccount.exception.BankAccountNotFoundException;
import com.jhippolyte.bankaccount.exception.DepositNotAllowedException;
import com.jhippolyte.bankaccount.model.BankAccount;
import com.jhippolyte.bankaccount.repository.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    SecurityService securityService;

    Logger logger = LoggerFactory.getLogger(BankAccountService.class);

    public BankAccount getAccount(String accountNumber) throws BankAccountNotFoundException {
        logger.info("Get Account with account number");
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new BankAccountNotFoundException("No bank account found for account number  : " + accountNumber);
        }
        return account;
    }

    public List<BankAccount> getAccounts(String userName) {
        return this.bankAccountRepository.findAll()
                .stream()
                .filter(bankAccount -> securityService.bankAccountIsAccessible(bankAccount,userName))
                .collect(Collectors.toList());
    }

    public double getBalance(String accountNumber) throws BankAccountNotFoundException {
        BankAccount account = getAccount(accountNumber);
        logger.info("Get Balance of the account");
        return account.getBalance();
    }

    public void withdraw(String accountNumber, double amount) throws BankAccountNotFoundException {
        BankAccount account = getAccount(accountNumber);
        account.withdraw(amount);
        bankAccountRepository.save(account);
    }

    public void deposit(String accountNumber, double amount) throws DepositNotAllowedException {
        BankAccount account = getAccount(accountNumber);
        account.deposit(amount);
        bankAccountRepository.save(account);
    }
}
