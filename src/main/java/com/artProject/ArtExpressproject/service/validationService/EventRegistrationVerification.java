package com.artProject.ArtExpressproject.service.validationService;

import com.artProject.ArtExpressproject.customException.AlreadyExistException;
import com.artProject.ArtExpressproject.customException.NotFoundException;
import com.artProject.ArtExpressproject.model.ExhibitionEventRegistration;
import com.artProject.ArtExpressproject.repository.ExhibitionEventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class EventRegistrationVerification {

    @Autowired
    private ExhibitionEventRegistrationRepository eventRegistrationRepository;

    public void verifyEmail(String email){
        boolean eventRegistration = eventRegistrationRepository.existsByEmail(email);
        if(eventRegistration){
            throw new AlreadyExistException("This email already exist....");
        }
    }

    public ExhibitionEventRegistration findByEmail(String email){
        ExhibitionEventRegistration findEmail = eventRegistrationRepository.findByEmail(email);
        if(findEmail == null){
            throw new NotFoundException("No such user with that email....");
        }
        else return  findEmail;

    }
}
