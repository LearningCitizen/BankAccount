package com.jhippolyte.bankaccount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountType {
    Current("current"),
    Saving("saving");

    private String type;
}
