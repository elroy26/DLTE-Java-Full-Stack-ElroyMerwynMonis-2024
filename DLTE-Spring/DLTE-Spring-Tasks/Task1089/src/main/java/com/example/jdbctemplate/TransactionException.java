package com.example.jdbctemplate;

import javax.smartcardio.CardException;

public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }
}
