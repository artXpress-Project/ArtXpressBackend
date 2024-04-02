package com.artProject.ArtExpressproject.customException;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String message){
        super(message);
    }
}
