package com.artProject.ArtExpressproject.artStudioTest;

import com.artProject.ArtExpressproject.customException.AlreadyExistException;
import com.artProject.ArtExpressproject.customException.NotFoundException;
import com.artProject.ArtExpressproject.dto.ArtStudioDto;
import com.artProject.ArtExpressproject.dto.requestDto.CreateArtStudioRequest;
import com.artProject.ArtExpressproject.dto.requestDto.UserRequest;
import com.artProject.ArtExpressproject.dto.responseDto.AuthResponse;
import com.artProject.ArtExpressproject.model.Address;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.ContactInformation;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.repository.AddressRepository;
import com.artProject.ArtExpressproject.repository.ArtStudioRepository;
import com.artProject.ArtExpressproject.repository.CartRepository;
import com.artProject.ArtExpressproject.repository.UserRepository;
import com.artProject.ArtExpressproject.service.ArtStudioService;
import com.artProject.ArtExpressproject.service.UserAuthServiceImpl;
import com.artProject.ArtExpressproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static com.artProject.ArtExpressproject.model.USER_ROLE.ROLE_ARTIST_OWNER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArtStudioTest {
    @Autowired
    private UserAuthServiceImpl userAuthService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ArtStudioRepository artStudioRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ArtStudioService artStudioService;

    @Autowired
    private AddressRepository addressRepository;

    private UserRequest userRequest;

    private CreateArtStudioRequest createArtStudioRequest;



    @BeforeEach
    public void startWith(){
        userRequest = new UserRequest();
        createArtStudioRequest = new CreateArtStudioRequest();


    }

    @BeforeEach
    public void tearDown(){
        artStudioRepository.deleteAll();
        cartRepository.deleteAll();
        userRepository.deleteAll();
        addressRepository.deleteAll();

    }

    @Test
    public void canRegisterAndCreateAnArtStudio(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

        User user1 = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("erfdgdgedfd");

        Address address = new Address();
        address.setCity("yaba");
        address.setStreetAddress("tefffgdgdfss");
        address.setPostalCode("3353526");
        address.setStateProvince("lagos");
        address.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("ikennajames@gmail.com");
        contactInformation.setMobileNumber("09134567891");
        contactInformation.setInstagram("refdgdgst");
        contactInformation.setTwitter("gdfdrdfd");
        contactInformation.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation);

        ArtStudio artStudio = artStudioService.createArtStudio(createArtStudioRequest,user1);
        assertNotNull(artStudio);
    }

    @Test
    public void canUpdateArtStudio(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

        User user1 = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("erfdgdgedfd");

        Address address = new Address();
        address.setCity("yaba");
        address.setStreetAddress("tefffgdgdfss");
        address.setPostalCode("3353526");
        address.setStateProvince("lagos");
        address.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("ikennajames@gmail.com");
        contactInformation.setMobileNumber("09134567891");
        contactInformation.setInstagram("refdgdgst");
        contactInformation.setTwitter("gdfdrdfd");
        contactInformation.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation);

        ArtStudio artStudio = artStudioService.createArtStudio(createArtStudioRequest,user1);
        assertNotNull(artStudio);
        System.out.println(artStudio);

        Long id = artStudio.getId();
        System.out.println(id);

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("sabo");
        createArtStudioRequest.setOpeningHours("8:30");
        createArtStudioRequest.setBusinessName("unique");

        Address newAddress = artStudio.getAddress();
        newAddress.setCity("yaba");
        newAddress.setStreetAddress("express way");
        newAddress.setPostalCode("10111");
        newAddress.setStateProvince("lagos");
        newAddress.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);
        addressRepository.save(newAddress);


        ArtStudio update = artStudioService.updateArtStudio(id,createArtStudioRequest);
        assertNotNull(update);

    }

    @Test
    public void deleteArtStudio(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

        User user1 = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("erfdgdgedfd");

        Address address = new Address();
        address.setCity("yaba");
        address.setStreetAddress("tefffgdgdfss");
        address.setPostalCode("3353526");
        address.setStateProvince("lagos");
        address.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("ikennajames@gmail.com");
        contactInformation.setMobileNumber("09134567891");
        contactInformation.setInstagram("refdgdgst");
        contactInformation.setTwitter("gdfdrdfd");
        contactInformation.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation);

        ArtStudio artStudio = artStudioService.createArtStudio(createArtStudioRequest,user1);
        assertNotNull(artStudio);
        Long id = artStudio.getId();
        ArtStudio artworkIds = artStudioService.findArtStudioById(id);
        assertNotNull(artworkIds);
        artStudioService.deleteArtStudio(id);
        assertThrows(NotFoundException.class, () ->  artStudioService.findArtStudioById(id));

    }

    @Test
    public void canGetAllCreatedArtStudio(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

        User user1 = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("erfdgdgedfd");

        Address address = new Address();
        address.setCity("yaba");
        address.setStreetAddress("tefffgdgdfss");
        address.setPostalCode("3353526");
        address.setStateProvince("lagos");
        address.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("ikennajames@gmail.com");
        contactInformation.setMobileNumber("09134567891");
        contactInformation.setInstagram("refdgdgst");
        contactInformation.setTwitter("gdfdrdfd");
        contactInformation.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation);

        ArtStudio artStudio = artStudioService.createArtStudio(createArtStudioRequest,user1);
        assertNotNull(artStudio);

        userRequest.setEmail("junior11james@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08189661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user2 = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user2);

        User registered = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("mymy");

        Address address2 = new Address();
        address2.setCity("yaba");
        address2.setStreetAddress("tefffgdgdfss");
        address2.setPostalCode("3353526");
        address2.setStateProvince("lagos");
        address2.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address2);

        ContactInformation contactInformation2 = new ContactInformation();
        contactInformation2.setEmail("ikennajames44@gmail.com");
        contactInformation2.setMobileNumber("09134567891");
        contactInformation2.setInstagram("refdgdgst");
        contactInformation2.setTwitter("gdfdrdfd");
        contactInformation2.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation2);

        ArtStudio artStudio2 = artStudioService.createArtStudio(createArtStudioRequest,registered);
        assertNotNull(artStudio2);

        List<ArtStudio> list = artStudioService.getAllArtStudio();
        assertNotNull(list);

    }

    @Test
    public void canSearchByQuery(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

        User user1 = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("erfdgdgedfd");

        Address address = new Address();
        address.setCity("yaba");
        address.setStreetAddress("tefffgdgdfss");
        address.setPostalCode("3353526");
        address.setStateProvince("lagos");
        address.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("ikennajames@gmail.com");
        contactInformation.setMobileNumber("09134567891");
        contactInformation.setInstagram("refdgdgst");
        contactInformation.setTwitter("gdfdrdfd");
        contactInformation.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation);

        ArtStudio artStudio = artStudioService.createArtStudio(createArtStudioRequest,user1);
        assertNotNull(artStudio);

        userRequest.setEmail("junior11james@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08189661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user2 = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user2);

        User registered = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("mymy");

        Address address2 = new Address();
        address2.setCity("yaba");
        address2.setStreetAddress("tefffgdgdfss");
        address2.setPostalCode("3353526");
        address2.setStateProvince("lagos");
        address2.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address2);

        ContactInformation contactInformation2 = new ContactInformation();
        contactInformation2.setEmail("ikennajames44@gmail.com");
        contactInformation2.setMobileNumber("09134567891");
        contactInformation2.setInstagram("refdgdgst");
        contactInformation2.setTwitter("gdfdrdfd");
        contactInformation2.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation2);

        ArtStudio artStudio2 = artStudioService.createArtStudio(createArtStudioRequest,registered);
        assertNotNull(artStudio2);

        List<ArtStudio> search = artStudioService.searchArtStudio("mymy");
        System.out.println(search);
        assertNotNull(search);
        List<ArtStudio> find = artStudioService.searchArtStudio("ikenna");
        System.out.println(find);
        assertNotNull(find);

    }

    @Test
    public void canFindArtStudioById(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

        User user1 = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("erfdgdgedfd");

        Address address = new Address();
        address.setCity("yaba");
        address.setStreetAddress("tefffgdgdfss");
        address.setPostalCode("3353526");
        address.setStateProvince("lagos");
        address.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("ikennajames@gmail.com");
        contactInformation.setMobileNumber("09134567891");
        contactInformation.setInstagram("refdgdgst");
        contactInformation.setTwitter("gdfdrdfd");
        contactInformation.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation);

        ArtStudio artStudio = artStudioService.createArtStudio(createArtStudioRequest,user1);
        assertNotNull(artStudio);
        Long id = artStudio.getId();

        ArtStudio find = artStudioService.findArtStudioById(id);
        assertNotNull(find);
    }

    @Test
    public void canFindArtStudioByUserId(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

        User user1 = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("erfdgdgedfd");

        Address address = new Address();
        address.setCity("yaba");
        address.setStreetAddress("tefffgdgdfss");
        address.setPostalCode("3353526");
        address.setStateProvince("lagos");
        address.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("ikennajames@gmail.com");
        contactInformation.setMobileNumber("09134567891");
        contactInformation.setInstagram("refdgdgst");
        contactInformation.setTwitter("gdfdrdfd");
        contactInformation.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation);

        ArtStudio artStudio = artStudioService.createArtStudio(createArtStudioRequest,user1);
        assertNotNull(artStudio);
        Long id = artStudio.getOwner().getId();

        ArtStudio find = artStudioService.getArtStudioByUserId(id);
        assertNotNull(find);
    }


    @Test
    public void canUpdateArStudio(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

        User user1 = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("erfdgdgedfd");

        Address address = new Address();
        address.setCity("yaba");
        address.setStreetAddress("tefffgdgdfss");
        address.setPostalCode("3353526");
        address.setStateProvince("lagos");
        address.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("ikennajames@gmail.com");
        contactInformation.setMobileNumber("09134567891");
        contactInformation.setInstagram("refdgdgst");
        contactInformation.setTwitter("gdfdrdfd");
        contactInformation.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation);

        ArtStudio artStudio = artStudioService.createArtStudio(createArtStudioRequest,user1);
        assertNotNull(artStudio);
        Long id = artStudio.getId();

        ArtStudio updateOpenStatus = artStudioService.updateArtStudioStatus(id);
        assertNotNull(updateOpenStatus);

        ArtStudio update = artStudioService.updateArtStudioStatus(id);
        assertNotNull(update);

        ArtStudio updateStatus = artStudioService.updateArtStudioStatus(id);
        assertNotNull(updateStatus);


    }

    @Test
    public void canAddFavourite(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

        User user1 = userService.findUserByEmail(userRequest.getEmail());

        createArtStudioRequest.setImages(List.of("https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww","https://plus.unsplash.com/premium_photo-1663047617262-6edc28d503c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0JTIwc3R1ZGlvfGVufDB8fDB8fHww"));
        createArtStudioRequest.setDescription("retetddee");
        createArtStudioRequest.setOpeningHours("9:00");
        createArtStudioRequest.setBusinessName("erfdgdgedfd");

        Address address = new Address();
        address.setCity("yaba");
        address.setStreetAddress("tefffgdgdfss");
        address.setPostalCode("3353526");
        address.setStateProvince("lagos");
        address.setCountry("Nigeria");
        createArtStudioRequest.setAddress(address);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("ikennajames@gmail.com");
        contactInformation.setMobileNumber("09134567891");
        contactInformation.setInstagram("refdgdgst");
        contactInformation.setTwitter("gdfdrdfd");
        contactInformation.setLinkedin("tererd");
        createArtStudioRequest.setContactInformation(contactInformation);

        ArtStudio artStudio = artStudioService.createArtStudio(createArtStudioRequest,user1);

        ArtStudioDto artStudioDto = new ArtStudioDto();
        artStudioDto.setBusinessName(artStudio.getBusinessName());
        artStudioDto.setDescription(artStudio.getDescription());
        artStudioDto.setImages(artStudio.getImages());

        ArtStudioDto artStudioDto1 = artStudioService.addFavorites(artStudio.getId(),user1);
        System.out.println(artStudioDto1);
        assertNotNull(artStudioDto1);
        System.out.println(artStudio);

        ArtStudioDto artStudioDtoSecondClick = artStudioService.addFavorites(artStudio.getId(),user1);
        System.out.println(artStudioDtoSecondClick);
        assertNotNull(artStudioDtoSecondClick);
        System.out.println(artStudio);

        ArtStudioDto artStudioDtoOnClick = artStudioService.addFavorites(artStudio.getId(),user1);
        System.out.println(artStudioDtoOnClick);
        assertNotNull(artStudioDtoOnClick);
        System.out.println(artStudio);

    }














}
