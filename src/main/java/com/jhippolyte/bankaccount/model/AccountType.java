package com.jhippolyte.bankaccount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum AccountType {
    CURRENT("CURRENT"),
    SAVING("SAVING");

    private String type;
}
