package com.artProject.ArtExpressproject.controller;

import com.artProject.ArtExpressproject.dto.requestDto.RegistrationRequest;
import com.artProject.ArtExpressproject.model.ExhibitionEventRegistration;
import com.artProject.ArtExpressproject.service.ExhibitionRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private ExhibitionRegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<ExhibitionEventRegistration> register(@RequestBody RegistrationRequest request){
        ExhibitionEventRegistration registration = registrationService.register(request);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

    @GetMapping("/registeredCustomers")
    public ResponseEntity<List<ExhibitionEventRegistration>> getAllCustomers(){
        List<ExhibitionEventRegistration> allCustomers = registrationService.getALLRegisteredUser();
        return new ResponseEntity<>(allCustomers,HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<ExhibitionEventRegistration> getUser(@RequestBody String email){
        ExhibitionEventRegistration event = registrationService.findRegisteredUser(email);
        return new ResponseEntity<>(event,HttpStatus.OK);
    }
}
