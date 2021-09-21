package com.jhippolyte.bankaccount.exception;

public class DepositNotAllowedException extends RuntimeException {

    public DepositNotAllowedException(String message){
        super(message);
    }
}
