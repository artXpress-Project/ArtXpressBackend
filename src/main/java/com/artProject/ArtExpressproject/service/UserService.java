package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.model.User;

public interface UserService {
    public User findUserByJwt(String jwt);

    public User findUserByEmail(String email);
}
