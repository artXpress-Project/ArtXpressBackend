package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.ArtStudioDto;
import com.artProject.ArtExpressproject.dto.requestDto.CreateArtStudioRequest;
import com.artProject.ArtExpressproject.model.Address;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.repository.AddressRepository;
import com.artProject.ArtExpressproject.repository.ArtStudioRepository;
import com.artProject.ArtExpressproject.repository.UserRepository;
import com.artProject.ArtExpressproject.service.ArtStudioService;
import com.artProject.ArtExpressproject.service.UserService;
import com.artProject.ArtExpressproject.service.validationService.ArtStudioVerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtStudioServiceImpl implements ArtStudioService {

    @Autowired
    private ArtStudioRepository artStudioRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArtStudioVerificationService artStudioVerificationService;


    @Override
    public ArtStudio createArtStudio(CreateArtStudioRequest request, User user) {
        artStudioVerificationService.ifBusinessNameExist(request.getBusinessName());
        Address address = addressRepository.save(request.getAddress());
        ArtStudio artStudio = modelMapper.map(request,ArtStudio.class);
        artStudio.setAddress(address);
        artStudio.setOpen(false);
        artStudio.setContactInformation(request.getContactInformation());
        artStudio.setOwner(user);


        return artStudioRepository.save(artStudio);
    }

    @Override
    public ArtStudio updateArtStudio(Long artStudioId, CreateArtStudioRequest updateRequest) {
         ArtStudio artStudio = findArtStudioById(artStudioId);
         if(artStudio.getBusinessName() != null) artStudio.setBusinessName(updateRequest.getBusinessName());
         if(artStudio.getDescription() != null) artStudio.setDescription(updateRequest.getDescription());
         if(artStudio.getImages() != null)artStudio.setImages(updateRequest.getImages());
         if(artStudio.getOpeningHours() != null)artStudio.setOpeningHours(updateRequest.getOpeningHours());
         updateContactAndAddressInfo(artStudio,updateRequest);
        return artStudioRepository.save(artStudio);
    }

    private void updateContactAndAddressInfo(ArtStudio artStudio, CreateArtStudioRequest updateRequest){
        if(artStudio.getContactInformation() != null)artStudio.setContactInformation(updateRequest.getContactInformation());
        if(artStudio.getAddress() != null) artStudio.setAddress(updateRequest.getAddress());

    }


    @Override
    public void deleteArtStudio(Long artStudioId) {
        ArtStudio artStudio = findArtStudioById(artStudioId);
        artStudioRepository.delete(artStudio);

    }

    @Override
    public List<ArtStudio> getAllRestaurant() {
        return artStudioRepository.findAll();
    }

    @Override
    public List<ArtStudio> searchArtStudio(String keyword) {
        return artStudioVerificationService.ifSearchByKeywordExist(keyword);
    }

    @Override
    public ArtStudio findArtStudioById(Long id) {
        return artStudioVerificationService.findArtStudioById(id);
    }

    @Override
    public ArtStudio getArtStudioByUserId(Long userId) {
        return artStudioVerificationService.findByOwnerId(userId);
    }

    @Override
    public ArtStudioDto addFavorites(Long artStudioId, User user) {
        ArtStudio artStudio =findArtStudioById(artStudioId);
        ArtStudioDto dto = createRestaurantDto(artStudioId,artStudio);
        updateFavourites(user,dto);


        return dto;
    }

    private ArtStudioDto createRestaurantDto(Long artStudioId, ArtStudio artStudio) {
        ArtStudioDto dto = new ArtStudioDto();
        dto.setDescription(artStudio.getDescription());
        dto.setImages(artStudio.getImages());
        dto.setBusinessName(artStudio.getBusinessName());
        dto.setId(artStudioId);
        return dto;
    }

    private void updateFavourites(User user, ArtStudioDto dto){
        boolean isFavorite = false;
        List<ArtStudioDto> favorites = user.getFavorites();
        for(ArtStudioDto fav : favorites){
            if (fav.getId().equals(dto.getId())){
                isFavorite = true;
                break;
            }
        }
        if(isFavorite) favorites.removeIf(fav->fav.getId().equals(dto.getId()));
        else favorites.add(dto);
        userRepository.save(user);

    }





    @Override
    public ArtStudio updateRestaurantStatus(Long id) {
        ArtStudio artStudio = findArtStudioById(id);
        artStudio.setOpen(!artStudio.isOpen());
        return artStudioRepository.save(artStudio);
    }
}
