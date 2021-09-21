package com.jhippolyte.bankaccount.model;

public enum PersonRole {
    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    PersonRole(String role){
        this.role = role;
    }
}
