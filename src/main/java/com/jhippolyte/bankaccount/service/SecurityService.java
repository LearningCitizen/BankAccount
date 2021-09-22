package com.jhippolyte.bankaccount.service;

import com.jhippolyte.bankaccount.model.BankAccount;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public static final String ADMIN_MAIL = "admin@jhippolyte.com";

    public boolean bankAccountIsAccessible(BankAccount bankAccount, String userMail) {
        if (!userMail.equals(ADMIN_MAIL) && (!bankAccount.getOwner().getMail().equals(userMail))) {
            return false;
        }
        return true;
    }
}
