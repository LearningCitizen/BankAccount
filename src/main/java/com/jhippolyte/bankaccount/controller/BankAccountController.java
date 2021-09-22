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

@RestController
@RequestMapping(path = "/api/v1/bank/accounts")
@Api(value = "bank-account-api")
public class BankAccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    MapService mapService;

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllAccounts(Principal principal) {
        LOGGER.info("Getting all the accounts of " + principal.getName());
        return ResponseEntity.ok(this.bankAccountService.getAccounts(principal.getName()));
    }


    @GetMapping(path = "/{number}")
    public ResponseEntity<BankAccountDto> getAccount(Principal principal, @PathVariable String number) {
        LOGGER.info("Getting account of account number : " + number);
        BankAccountDto bankAccountDto = mapService.mapToDto((bankAccountService.getAccount(principal.getName(), number)));
        ResponseEntity<BankAccountDto> responseEntity = ResponseEntity.ok(bankAccountDto);
        return responseEntity;
    }

    @GetMapping(path = "/{number}/balance/")
    public ResponseEntity<Double> getAccountBalance(Principal principal, @PathVariable String number) {
        LOGGER.info("Getting balance of account number : " + number);
        ResponseEntity<Double> responseEntity = ResponseEntity.ok(bankAccountService.getBalance(principal.getName(), number));
        return responseEntity;
    }

    @PutMapping(path = "/{number}/withdrawal")
    public ResponseEntity<String> withdraw(Principal principal, @PathVariable String number, @RequestParam Double amount) {
        LOGGER.info("Withdrawing " + amount + " from account with account number : " + number);
        this.bankAccountService.withdraw(principal.getName(), number, amount);
        return ResponseEntity.ok("Withwdrawal of " + amount + " done");
    }

    @PutMapping(path = "/{number}/deposit")
    public ResponseEntity<String> deposit(Principal principal, @PathVariable String number, @RequestParam Double amount) {
        LOGGER.info("Depositing " + amount + " on account with account number : " + number);
        this.bankAccountService.deposit(principal.getName(), number, amount);
        return ResponseEntity.ok("Deposit of " + amount + " done");
    }
}
