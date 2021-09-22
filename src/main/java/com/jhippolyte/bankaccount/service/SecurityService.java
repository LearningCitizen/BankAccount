package com.jhippolyte.bankaccount.service;

import com.jhippolyte.bankaccount.model.BankAccount;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public static final String ADMIN_MAIL = "admin@jhippolyte.com";

    public boolean bankAccountIsAccessible(String userMail, BankAccount bankAccount) {
        if (!userMail.equals(ADMIN_MAIL) && (!bankAccount.getOwner().getMail().equals(userMail))) {
            return false;
        }
        return true;
    }

    public void checkUserAccessToAccount(String userMail, BankAccount bankAccount){
        if (!bankAccountIsAccessible(userMail, bankAccount)){
            throw new SecurityException("You are not allowed to access the bank account "+bankAccount.getAccountNumber());
        }
    }
}
