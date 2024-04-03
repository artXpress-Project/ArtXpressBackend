package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.config.JwtProvider;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.service.validationService.EmailValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private EmailValidationService emailValidationService;
    @Override
    public User findUserByJwt(String jwt) {
     String email =  jwtProvider.getEmailFromJwtToken(jwt);
     return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
       return emailValidationService.findEmail(email);


    }
}
