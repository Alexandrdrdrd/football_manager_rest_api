package com.example.taskrestapi.exception_handling;

public class NoSuchPlayerException extends RuntimeException{
    public NoSuchPlayerException(String message) {
        super(message);
    }
}
