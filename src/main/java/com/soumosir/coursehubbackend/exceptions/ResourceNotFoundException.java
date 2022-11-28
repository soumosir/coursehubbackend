package com.soumosir.coursehubbackend.exceptions;
import  java.lang.RuntimeException;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
