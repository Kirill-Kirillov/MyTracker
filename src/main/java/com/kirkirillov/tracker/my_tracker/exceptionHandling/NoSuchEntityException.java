package com.kirkirillov.tracker.my_tracker.exceptionHandling;

public class NoSuchEntityException extends RuntimeException{
    public NoSuchEntityException(String message) {
        super(message);
    }
}
