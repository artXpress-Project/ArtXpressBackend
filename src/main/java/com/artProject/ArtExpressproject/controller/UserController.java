package com.artProject.ArtExpressproject.controller;

import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
