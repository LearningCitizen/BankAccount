package com.jhippolyte.bankaccount.dto;

import com.jhippolyte.bankaccount.model.AccountType;
import lombok.Data;

@Data
public class BankAccountDto {

    private PersonDto owner;

    private String accountNumber;

    private Double balance = 50.00; //An account is created initially with 50 EUROS

    private AccountType type;

    private Double overdraft = 0.00; //No overdraft allowed
}
