package com.jhippolyte.bankaccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { BankAccountNotFoundException.class })
    public ResponseEntity <String> handleBankAccountNotFoundException(BankAccountNotFoundException ex, WebRequest webRequest){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = { WithdrawNotAllowedException.class, DepositNotAllowedException.class })
    public ResponseEntity <String> handleWithdrawNotAllowedException(Exception ex, WebRequest webRequest){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = { SecurityException.class })
    public ResponseEntity <String> handleSecurityException(Exception ex, WebRequest webRequest){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }
}
