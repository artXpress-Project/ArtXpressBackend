package com.artProject.ArtExpressproject.userServiceTest;

import com.artProject.ArtExpressproject.dto.requestDto.SignInRequest;
import com.artProject.ArtExpressproject.dto.requestDto.UserRequest;
import com.artProject.ArtExpressproject.dto.responseDto.AuthResponse;
import com.artProject.ArtExpressproject.model.USER_ROLE;
import com.artProject.ArtExpressproject.repository.ArtStudioRepository;
import com.artProject.ArtExpressproject.repository.CartRepository;
import com.artProject.ArtExpressproject.repository.UserRepository;
import com.artProject.ArtExpressproject.service.UserAuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.artProject.ArtExpressproject.model.USER_ROLE.ROLE_ARTIST_OWNER;
import static com.artProject.ArtExpressproject.model.USER_ROLE.ROLE_COLLECTOR;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserAuthTest {

    @Autowired
    private UserAuthServiceImpl userAuthService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ArtStudioRepository artStudioRepository;

    private UserRequest userRequest;

    private SignInRequest signInRequest;

    @BeforeEach
    public void startWith(){
        userRequest = new UserRequest();
        signInRequest = new SignInRequest();

    }

    @BeforeEach
    public void tearDown(){
        artStudioRepository.deleteAll();
        cartRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    public void registerUserAsArtist(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);
    }

    @Test
    public void registerUserAsCollector(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_COLLECTOR);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);

    }

    @Test
    public void UserCanSignIn(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        System.out.println(user.getJwt());
        assertNotNull(user);
        signInRequest.setEmail("juniorjames@gmail.com");
        signInRequest.setPassword("1234");
        AuthResponse response = userAuthService.SignIn(signInRequest);
        System.out.println(response.getJwt());
        assertNotNull(response);

    }


}
