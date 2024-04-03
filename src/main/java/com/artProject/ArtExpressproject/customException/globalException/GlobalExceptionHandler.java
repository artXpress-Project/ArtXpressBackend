package com.artProject.ArtExpressproject.customException.globalException;


import com.artProject.ArtExpressproject.customException.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    public Map<String,String> alreadyExistException(AlreadyExistException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exception.getMessage());
        return errorMap;

    }

    @ExceptionHandler(EmailNotFoundException.class)
    public Map<String,String> emailNotFoundException(EmailNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exception.getMessage());
        return errorMap;

    }

    @ExceptionHandler(NotFoundException.class)
    public Map<String,String> notFoundException(NotFoundException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exception.getMessage());
        return errorMap;

    }

    @ExceptionHandler(OrderException.class)
    public Map<String,String> handleUserException(OrderException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exception.getMessage());
        return errorMap;

    }

    @ExceptionHandler(InvalidEmailException.class)
    public Map<String,String> handleInvalidEmailException(InvalidEmailException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exception.getMessage());
        return errorMap;

    }

    @ExceptionHandler(BadCredentialsException.class)
    public Map<String,String> badCredentialException(BadCredentialsException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exception.getMessage());
        return errorMap;

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Map<String,String> UserNameNotFoundException(UsernameNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exception.getMessage());
        return errorMap;

    }


}
