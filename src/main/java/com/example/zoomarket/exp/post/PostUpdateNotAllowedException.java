package com.example.zoomarket.exp.post;

public class PostUpdateNotAllowedException extends RuntimeException {
    public PostUpdateNotAllowedException(String message) {
        super(message);
    }
}
