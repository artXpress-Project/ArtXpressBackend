package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.config.JwtProvider;
import com.artProject.ArtExpressproject.dto.requestDto.SignInRequest;
import com.artProject.ArtExpressproject.dto.requestDto.UserRequest;
import com.artProject.ArtExpressproject.dto.responseDto.AuthResponse;
import com.artProject.ArtExpressproject.model.Cart;
import com.artProject.ArtExpressproject.model.USER_ROLE;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.repository.CartRepository;
import com.artProject.ArtExpressproject.repository.UserRepository;
import com.artProject.ArtExpressproject.service.validationService.EmailValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserAuthServiceImpl implements UserAuthService{
     @Autowired
    private UserRepository userRepository;

     @Autowired
     private PasswordEncoder passwordEncoder;

    @Autowired
     private JwtProvider jwtProvider;

    @Autowired
     private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailValidationService emailValidationService;

    @Override
    public AuthResponse registerUserHandler(UserRequest userRequest) {
        EmailValidationService.verifyEmailFormat(userRequest.getEmail());
        emailValidationService.ifUserEmailAlreadyExist(userRequest.getEmail());
        User createUser = modelMapper.map(userRequest,User.class);
        createUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User saveUser = userRepository.save(createUser);
        addIntoCart(saveUser);
        return authentication(userRequest.getEmail(),userRequest.getPassword(),userRequest.getRole());

    }

    @Override
    public AuthResponse SignIn(SignInRequest signInRequest) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();
        Authentication authentication = authenticate(email,password);

        Collection<? extends GrantedAuthority>authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);
        return loginAuthenticate(jwt,USER_ROLE.valueOf(role));
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setJwt(jwt);
//        authResponse.setMessage("Login Successful");
//        authResponse.setUser(USER_ROLE.valueOf(role));
//        return authResponse;
    }

    private void addIntoCart(User user){
        Cart cart = new Cart();
        cart.setCollector(user);
        cartRepository.save(cart);
    }

    private AuthResponse loginAuthenticate(String jwt,USER_ROLE role){
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successful");
        authResponse.setUser(role);
        return authResponse;

    }

    private AuthResponse authentication(String email, String password, USER_ROLE user){
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration Successful");
        authResponse.setUser(user);
        return authResponse;

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid userEmail....");
        }if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password.....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
