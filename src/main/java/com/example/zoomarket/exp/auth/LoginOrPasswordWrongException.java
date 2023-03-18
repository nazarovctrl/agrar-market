package com.example.zoomarket.exp.auth;

public class LoginOrPasswordWrongException extends RuntimeException {
    public LoginOrPasswordWrongException(String message) {
        super(message);
    }
}
