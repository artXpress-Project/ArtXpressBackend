package com.artProject.ArtExpressproject.controller;

import com.artProject.ArtExpressproject.dto.requestDto.SignInRequest;
import com.artProject.ArtExpressproject.dto.requestDto.UserRequest;
import com.artProject.ArtExpressproject.dto.responseDto.AuthResponse;
import com.artProject.ArtExpressproject.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody UserRequest userRequest){
      AuthResponse authResponse =  userAuthService.registerUserHandler(userRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest signInRequest) {
        AuthResponse authResponse = userAuthService.SignIn(signInRequest);
        return new ResponseEntity<>(authResponse,HttpStatus.OK);

    }
}
