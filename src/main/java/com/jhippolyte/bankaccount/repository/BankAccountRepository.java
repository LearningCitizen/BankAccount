package com.jhippolyte.bankaccount.repository;

import com.jhippolyte.bankaccount.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

   BankAccount findByAccountNumber (String accountNumber);
}
