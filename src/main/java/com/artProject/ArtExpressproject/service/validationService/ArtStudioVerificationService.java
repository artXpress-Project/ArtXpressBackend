package com.artProject.ArtExpressproject.service.validationService;

import com.artProject.ArtExpressproject.customException.AlreadyExistException;
import com.artProject.ArtExpressproject.customException.NotFoundException;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.repository.ArtStudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ArtStudioVerificationService {
    @Autowired
    private ArtStudioRepository artStudioRepository;


    public void ifBusinessNameExist(String businessName){
        ArtStudio artStudio = artStudioRepository.findByBusinessName(businessName);
        if(artStudio != null){
            throw new AlreadyExistException("Business Already Exist");
        }
    }

    public List<ArtStudio> ifSearchByKeywordExist(String keyword){
       List<ArtStudio> artStudio = artStudioRepository.findBySearchQuery(keyword);
       if(artStudio.isEmpty()) throw new NotFoundException("Art studio not found for: " + keyword);
       else return artStudio;
    }

    public ArtStudio findArtStudioById(Long id){
        Optional<ArtStudio> artStudio = artStudioRepository.findById(id);
        if(artStudio.isEmpty()) throw new NotFoundException("Invalid Restaurant");
        else return artStudio.get();

    }
    public ArtStudio findByOwnerId(Long userId){
        ArtStudio artStudio = artStudioRepository.findByOwnerId(userId);
        if(artStudio == null) throw new NotFoundException("ArtStudio not found");
        else return artStudio;
    }
}
