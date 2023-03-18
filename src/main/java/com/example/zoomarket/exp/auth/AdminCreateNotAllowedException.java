package com.example.zoomarket.exp.auth;


public class AdminCreateNotAllowedException extends RuntimeException {

    public AdminCreateNotAllowedException(String message) {
        super(message);
    }
}
