package com.artProject.ArtExpressproject.customException;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
