package com.example.zoomarket.exp.auth;


public class InCorrectPhoneNumberException extends RuntimeException {

    public InCorrectPhoneNumberException(String message) {
        super(message);
    }
}
