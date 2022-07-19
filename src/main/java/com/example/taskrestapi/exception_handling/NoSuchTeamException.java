package com.example.taskrestapi.exception_handling;

public class NoSuchTeamException extends RuntimeException {
    public NoSuchTeamException(String message) {
        super(message);
    }
}
