package com.eteration.simplebanking.model;


public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super("Insufficient balance in the account");
    }
}
