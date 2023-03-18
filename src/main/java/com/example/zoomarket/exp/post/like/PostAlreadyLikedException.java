package com.example.zoomarket.exp.post.like;

public class PostAlreadyLikedException extends RuntimeException {
    public PostAlreadyLikedException(String message) {
        super(message);
    }
}
