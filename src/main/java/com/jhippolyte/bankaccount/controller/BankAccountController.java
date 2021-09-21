package com.jhippolyte.bankaccount.controller;

import com.jhippolyte.bankaccount.model.BankAccount;
import com.jhippolyte.bankaccount.service.BankAccountService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/bank/accounts")
@Api(value = "bank-account-api")
public class BankAccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping(path = "/{number}")
    public ResponseEntity<BankAccount> getAccount(@RequestParam String accountNumber) {
        LOGGER.info("Getting account of account number : " + accountNumber);
        ResponseEntity<BankAccount> responseEntity = ResponseEntity.ok(bankAccountService.getAccount(accountNumber));
        return responseEntity;
    }

    @GetMapping(path = "/{number}/balance/")
    public ResponseEntity<Double> getAccountBalance(@PathVariable String accountNumber) {
        LOGGER.info("Getting balance of account number : " + accountNumber);
        ResponseEntity<Double> responseEntity = ResponseEntity.ok(bankAccountService.getBalance(accountNumber));
        return responseEntity;
    }
}
