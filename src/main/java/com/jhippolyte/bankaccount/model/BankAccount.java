package com.jhippolyte.bankaccount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BankAccount {
    private Person owner;
    private String password;
    private Double balance = 50.00; //An account is created initially with 50 EUROS
    private AccountType type;
    private Double overdraft = 0.00; //No overdraft allowed

    public void withdraw(double amount){
        this.balance = balance - amount;
    }

    public void deposit(double amount){
        this.balance = balance + amount;
    }

}
