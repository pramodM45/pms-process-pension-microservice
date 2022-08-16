package com.example.processpensionmicroservice.exceptions;

public class AadhaarNotFoundException extends RuntimeException{
    public AadhaarNotFoundException(String message) {
        super(message);
    }
}
