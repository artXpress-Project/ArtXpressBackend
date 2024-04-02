package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.requestDto.RegistrationRequest;
import com.artProject.ArtExpressproject.model.ExhibitionEventRegistration;
import com.artProject.ArtExpressproject.repository.ExhibitionEventRegistrationRepository;
import com.artProject.ArtExpressproject.service.validationService.EventRegistrationVerification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExhibitionRegistrationServiceImpl implements ExhibitionRegistrationService{

    @Autowired
    private EventRegistrationVerification eventVerification;

    @Autowired
    private ExhibitionEventRegistrationRepository exhibitionRepository;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public ExhibitionEventRegistration register(RegistrationRequest register) {
        eventVerification.verifyEmail(register.getEmail());
        ExhibitionEventRegistration eventRegister = modelMapper.map(register,ExhibitionEventRegistration.class);
        eventRegister.setDateRegistered(LocalDate.now());

        return exhibitionRepository.save(eventRegister);
    }

    @Override
    public List<ExhibitionEventRegistration> getALLRegisteredUser() {
        return exhibitionRepository.findAll();
    }

    @Override
    public ExhibitionEventRegistration findRegisteredUser(String email) {

        return eventVerification.findByEmail(email);
    }
}
