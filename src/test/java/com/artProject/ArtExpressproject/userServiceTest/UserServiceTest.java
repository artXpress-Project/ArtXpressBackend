package com.artProject.ArtExpressproject.userServiceTest;

import com.artProject.ArtExpressproject.dto.requestDto.SignInRequest;
import com.artProject.ArtExpressproject.dto.requestDto.UserRequest;
import com.artProject.ArtExpressproject.dto.responseDto.AuthResponse;
import com.artProject.ArtExpressproject.model.Order;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.repository.ArtStudioRepository;
import com.artProject.ArtExpressproject.repository.CartRepository;
import com.artProject.ArtExpressproject.repository.UserRepository;
import com.artProject.ArtExpressproject.service.UserAuthServiceImpl;
import com.artProject.ArtExpressproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.artProject.ArtExpressproject.model.USER_ROLE.ROLE_ARTIST_OWNER;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

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

    private UserRequest userRequest;



    @BeforeEach
    public void startWith(){
        userRequest = new UserRequest();


    }

    @BeforeEach
    public void tearDown(){
        artStudioRepository.deleteAll();
        cartRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    public void canGetUserProfile(){
        userRequest.setEmail("juniorjamess@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);
        System.out.println(user);
        User user1 = userService.findUserByEmail(userRequest.getEmail());
        System.out.println(user1);
        assertNotNull(user1);

    }


}
