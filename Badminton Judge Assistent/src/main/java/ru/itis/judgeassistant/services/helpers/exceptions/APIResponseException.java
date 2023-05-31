package ru.itis.judgeassistant.services.helpers.exceptions;

public class APIResponseException extends RuntimeException{
    public APIResponseException(String message) {
        super(message);
    }
}
