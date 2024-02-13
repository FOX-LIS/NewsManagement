package com.example.NewsManagement.exception;

public class ActionNotPermittedException extends RuntimeException{
    public ActionNotPermittedException(String message) {
        super(message);
    }
}
