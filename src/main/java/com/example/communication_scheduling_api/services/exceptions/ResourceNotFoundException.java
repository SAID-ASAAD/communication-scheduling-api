package com.example.communication_scheduling_api.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id){
        super("Resource not found with Id " + id );
    }

}
