package com.jhippolyte.bankaccount.controller;

import com.jhippolyte.bankaccount.dto.BankAccountDto;
import com.jhippolyte.bankaccount.model.BankAccount;
import com.jhippolyte.bankaccount.service.BankAccountService;
import com.jhippolyte.bankaccount.service.MapService;
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

    @Autowired
    MapService mapService;

    @GetMapping(path = "/{number}")
    public ResponseEntity<BankAccountDto> getAccount(@RequestParam String accountNumber) {
        LOGGER.info("Getting account of account number : " + accountNumber);
        BankAccountDto bankAccountDto = mapService.mapToDto((bankAccountService.getAccount(accountNumber)));
        ResponseEntity<BankAccountDto> responseEntity = ResponseEntity.ok(bankAccountDto);
        return responseEntity;
    }

    @GetMapping(path = "/{number}/balance/")
    public ResponseEntity<Double> getAccountBalance(@PathVariable String accountNumber) {
        LOGGER.info("Getting balance of account number : " + accountNumber);
        ResponseEntity<Double> responseEntity = ResponseEntity.ok(bankAccountService.getBalance(accountNumber));
        return responseEntity;
    }
}
