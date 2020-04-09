package com.enigma.restservice.exception;


public class EntityNotFoundException extends ApplicationException{

    public EntityNotFoundException() {
        super(ErrorCodes.ENTITY_NOT_FOUND, "exception.entity.not.found");
    }
    
}
