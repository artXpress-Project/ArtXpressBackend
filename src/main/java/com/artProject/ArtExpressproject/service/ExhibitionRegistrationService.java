package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.requestDto.RegistrationRequest;
import com.artProject.ArtExpressproject.model.ExhibitionEventRegistration;

import java.util.List;

public interface ExhibitionRegistrationService {

    ExhibitionEventRegistration register(RegistrationRequest register);

    List<ExhibitionEventRegistration> getALLRegisteredUser();

    ExhibitionEventRegistration findRegisteredUser(String email);


}
