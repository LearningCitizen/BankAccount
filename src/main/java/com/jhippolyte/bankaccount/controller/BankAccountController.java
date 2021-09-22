package com.jhippolyte.bankaccount.controller;

import com.jhippolyte.bankaccount.dto.BankAccountDto;
import com.jhippolyte.bankaccount.model.BankAccount;
import com.jhippolyte.bankaccount.service.BankAccountService;
import com.jhippolyte.bankaccount.service.MapService;
import com.jhippolyte.bankaccount.service.SecurityService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/bank/accounts")
@Api(value = "bank-account-api")
public class BankAccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    MapService mapService;

    @Autowired
    SecurityService securityService;

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllAccounts(Principal principal) {
        LOGGER.info("Getting all the accounts of " + principal.getName());
        return ResponseEntity.ok(this.bankAccountService.getAccounts()
                .stream().filter(bankAccount -> securityService.bankAccountIsAccessible(bankAccount, principal.getName()))
                .collect(Collectors.toList()));
    }


    @GetMapping(path = "/{number}")
    public ResponseEntity<BankAccountDto> getAccount(@PathVariable String number) {
        LOGGER.info("Getting account of account number : " + number);
        BankAccountDto bankAccountDto = mapService.mapToDto((bankAccountService.getAccount(number)));
        ResponseEntity<BankAccountDto> responseEntity = ResponseEntity.ok(bankAccountDto);
        return responseEntity;
    }

    @GetMapping(path = "/{number}/balance/")
    public ResponseEntity<Double> getAccountBalance(@PathVariable String number) {
        LOGGER.info("Getting balance of account number : " + number);
        ResponseEntity<Double> responseEntity = ResponseEntity.ok(bankAccountService.getBalance(number));
        return responseEntity;
    }

    @PutMapping(path = "/{number}/withdrawal")
    public ResponseEntity<String> withdraw(@PathVariable String number, @RequestParam Double amount) {
        LOGGER.info("Withdrawing " + amount + " from account with account number : " + number);
        this.bankAccountService.withdraw(number, amount);
        return ResponseEntity.ok("Withwdrawal of " + amount + " done");
    }

    @PutMapping(path = "/{number}/deposit")
    public ResponseEntity<String> deposit(@PathVariable String number, @RequestParam Double amount) {
        LOGGER.info("Depositing " + amount + " on account with account number : " + number);
        this.bankAccountService.deposit(number, amount);
        return ResponseEntity.ok("Deposit of " + amount + " done");
    }
}
