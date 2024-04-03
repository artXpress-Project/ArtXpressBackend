package com.artProject.ArtExpressproject.userServiceTest;

import com.artProject.ArtExpressproject.customException.AlreadyExistException;
import com.artProject.ArtExpressproject.customException.InvalidEmailException;
import com.artProject.ArtExpressproject.dto.requestDto.SignInRequest;
import com.artProject.ArtExpressproject.dto.requestDto.UserRequest;
import com.artProject.ArtExpressproject.dto.responseDto.AuthResponse;
import com.artProject.ArtExpressproject.repository.ArtStudioRepository;
import com.artProject.ArtExpressproject.repository.CartRepository;
import com.artProject.ArtExpressproject.repository.UserRepository;
import com.artProject.ArtExpressproject.service.UserAuthService;
import com.artProject.ArtExpressproject.service.UserAuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.artProject.ArtExpressproject.model.USER_ROLE.ROLE_ARTIST_OWNER;
import static com.artProject.ArtExpressproject.model.USER_ROLE.ROLE_COLLECTOR;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserAuthTest {

    @Autowired
    private UserAuthService userAuthService;

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

    @Test
    public void ExceptionIsThrownWhenUserTriesToRegisterMultipleTimesWithTheSameEmail(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_COLLECTOR);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        assertNotNull(user);
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("johnson");
        userRequest.setPassword("12345");
        userRequest.setPhoneNumber("08149771103");
        userRequest.setRole(ROLE_COLLECTOR);
        assertThrows(AlreadyExistException.class, () -> userAuthService.registerUserHandler(userRequest));


    }

    @Test
    public void ExceptionIsThrownWhenUserTriesToRegisterWithAnInvalidEmailFormat(){
        userRequest.setEmail("juniorjames@gmail");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_COLLECTOR);
        assertThrows(InvalidEmailException.class, () -> userAuthService.registerUserHandler(userRequest));

    }

    @Test
    public void ExceptionIsThrownWhenUserTriesToSignInWithWrongEmail(){
        userRequest.setEmail("juniorjames@gmail.com");
        userRequest.setFirstName("ikenna");
        userRequest.setLastName("june");
        userRequest.setPassword("1234");
        userRequest.setPhoneNumber("08149661103");
        userRequest.setRole(ROLE_ARTIST_OWNER);
        AuthResponse user = userAuthService.registerUserHandler(userRequest);
        System.out.println(user.getJwt());
        assertNotNull(user);
        signInRequest.setEmail("juniorssjames@gmail.com");
        signInRequest.setPassword("1234");
        assertThrows(UsernameNotFoundException.class, () -> userAuthService.SignIn(signInRequest));


    }

    @Test
    public void ExceptionIsThrownWhenUserTriesToSignInWithWrongPassword(){
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
        signInRequest.setPassword("123466");
        assertThrows(BadCredentialsException.class, () -> userAuthService.SignIn(signInRequest));


    }

    @Test
    public void ExceptionIsThrownWhenUserTriesToSignInWithOutRegistering(){
        signInRequest.setEmail("juniorjames@gmail.com");
        signInRequest.setPassword("123466");
        assertThrows(UsernameNotFoundException.class, () -> userAuthService.SignIn(signInRequest));


    }


}
