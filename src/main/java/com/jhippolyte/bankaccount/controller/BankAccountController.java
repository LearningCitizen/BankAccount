package com.jhippolyte.bankaccount.controller;

import com.jhippolyte.bankaccount.service.BankAccountService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/bank/accounts")
@Api(value = "bank-account-api")
public class BankAccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping(path = "/balance")
    public ResponseEntity<Double> getBalance(@RequestParam String accountNumber)
    {
        return ResponseEntity.ok(bankAccountService.getBalance(accountNumber));
    }
}
