package com.ylurexon.backend.exception;
import com.ylurexon.backend.exception.ResourceNotFoundException;

public class ResourceNotFoundException  extends RuntimeException {
    public ResourceNotFoundException(String message) {

        super(message);

    }
}
