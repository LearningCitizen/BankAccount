package com.jhippolyte.bankaccount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "bankAccounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "owner")
    private Person owner;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance = 50.00; //An account is created initially with 50 EUROS

    @Column(name = "type")
    private AccountType type;

    @Column(name = "overdraft")
    private Double overdraft = 0.00; //No overdraft allowed

    public void withdraw(double amount){
        this.balance = balance - amount;
    }

    public void deposit(double amount){
        this.balance = balance + amount;
    }

}
