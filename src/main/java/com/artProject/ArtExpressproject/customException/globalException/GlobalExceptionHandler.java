package com.artProject.ArtExpressproject.customException.globalException;


import com.artProject.ArtExpressproject.customException.AlreadyExistException;
import com.artProject.ArtExpressproject.customException.EmailNotFoundException;
import com.artProject.ArtExpressproject.customException.NotFoundException;
import com.artProject.ArtExpressproject.customException.OrderException;
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


}
