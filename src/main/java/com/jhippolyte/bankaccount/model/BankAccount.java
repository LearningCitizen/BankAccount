package com.jhippolyte.bankaccount.model;

import com.jhippolyte.bankaccount.exception.DepositNotAllowedException;
import com.jhippolyte.bankaccount.exception.WithdrawNotAllowedException;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "bankAccounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Person owner;

    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "balance")
    private Double balance = 50.00; //An account is created initially with 50 EUROS

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "overdraft")
    private Double overdraft = 0.00; //No overdraft allowed

    public static final double MAX_DEPOSIT_AUTHORIZED = 10000;
    private static final String WITHDRAWAL_ERROR_MESSAGE = "The amount %s EUR cannot be withdrawn because it exceeds the balance and the overdraft of the account";
    private static final String DEPOSIT_ERROR_MESSAGE = "The amount %s EUR cannot be deposited because it exceeds the maximum deposit authorized : %s EUR";

    public void withdraw(double amount) throws WithdrawNotAllowedException{
        if (balance + overdraft - amount < 0) {
            throw new WithdrawNotAllowedException(String.format(WITHDRAWAL_ERROR_MESSAGE, amount));
        }
        this.balance = balance - amount;
    }

    public void deposit(double amount){
        if(amount > MAX_DEPOSIT_AUTHORIZED){
            throw new DepositNotAllowedException(String.format(DEPOSIT_ERROR_MESSAGE, amount, MAX_DEPOSIT_AUTHORIZED));
        }
        this.balance = balance + amount;
    }

}
