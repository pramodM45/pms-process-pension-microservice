package com.example.processpensionmicroservice.exceptions;

public class JWTException extends RuntimeException {
    public JWTException(String message) {
        super(message);
    }

}
