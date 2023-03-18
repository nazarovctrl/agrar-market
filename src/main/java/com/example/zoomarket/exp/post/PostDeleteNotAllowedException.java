package com.example.zoomarket.exp.post;

public class PostDeleteNotAllowedException extends RuntimeException {
    public PostDeleteNotAllowedException(String message) {
        super(message);
    }
}
