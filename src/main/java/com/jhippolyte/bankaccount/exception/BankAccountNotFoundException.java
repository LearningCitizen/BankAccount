package com.jhippolyte.bankaccount.exception;

public class BankAccountNotFoundException extends RuntimeException {

    public BankAccountNotFoundException(String message){
        super(message);
    }
}
