package com.example.zoomarket.exp.auth;

public class IncorrectSMSCodeException extends RuntimeException {
    public IncorrectSMSCodeException(String message) {
        super(message);
    }
}
