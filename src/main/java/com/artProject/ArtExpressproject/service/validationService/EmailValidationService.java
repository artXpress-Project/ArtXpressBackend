package com.artProject.ArtExpressproject.service.validationService;

import com.artProject.ArtExpressproject.customException.AlreadyExistException;
import com.artProject.ArtExpressproject.customException.EmailNotFoundException;
import com.artProject.ArtExpressproject.customException.InvalidEmailException;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Validated
public class EmailValidationService {

    @Autowired
    private UserRepository userRepository;

    public static void  verifyEmailFormat(@Email String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) throw new InvalidEmailException("Invalid email");


    }
    public void ifUserEmailAlreadyExist(String email){
        User isEmailAlreadyExist = userRepository.findByEmail(email);
        if(isEmailAlreadyExist != null){
            throw new AlreadyExistException("Email Already exist with another Account");
        }
    }

    public User findEmail(String email){
        User findEmail = userRepository.findByEmail(email);
        if(findEmail == null){
            throw new EmailNotFoundException("Email does not exist");
        }
        return findEmail;
    }


}
