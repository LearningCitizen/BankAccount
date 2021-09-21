package com.jhippolyte.bankaccount.controller;

import com.jhippolyte.bankaccount.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/bank/account")
public class BankAccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    BankAccountService bankAccountService;

    public ResponseEntity<Double> getBalance(String accountNumber)
    {
        return ResponseEntity.ok(bankAccountService.getBalance(accountNumber));
    }
}
