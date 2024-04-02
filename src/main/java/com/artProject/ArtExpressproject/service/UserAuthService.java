package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.requestDto.SignInRequest;
import com.artProject.ArtExpressproject.dto.requestDto.UserRequest;
import com.artProject.ArtExpressproject.dto.responseDto.AuthResponse;
import com.artProject.ArtExpressproject.model.User;

public interface UserAuthService {
    AuthResponse registerUserHandler(UserRequest userRequest);

    AuthResponse SignIn(SignInRequest signInRequest);
}
