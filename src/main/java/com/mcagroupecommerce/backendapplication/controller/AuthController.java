package com.mcagroupecommerce.backendapplication.controller;

import com.mcagroupecommerce.backendapplication.config.JwtTokenProvider;
import com.mcagroupecommerce.backendapplication.exception.UserException;
import com.mcagroupecommerce.backendapplication.model.User;
import com.mcagroupecommerce.backendapplication.repository.UserRepository;
import com.mcagroupecommerce.backendapplication.request.LoginRequest;
import com.mcagroupecommerce.backendapplication.responses.AuthResponse;
import com.mcagroupecommerce.backendapplication.service.CartService;
import com.mcagroupecommerce.backendapplication.service.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private CustomUserDetails customUserDetails;
    private CartService cartService;

    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtTokenProvider jwtTokenProvider,CustomUserDetails customUserDetails,CartService cartService) {

        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtTokenProvider=jwtTokenProvider;
        this.customUserDetails=customUserDetails;
        this.cartService=cartService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException{

        String email = user.getEmail();
        String password = user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();

        User isEmailExist=userRepository.findByEmail(email);

        // Check if user with the given email already exists
        if (isEmailExist!=null) {
            // System.out.println("--------- exist "+isEmailExist).getEmail());

            throw new UserException("Email Is Already Used With Another Account");
        }

        // Create new user
        User createdUser= new User();
        createdUser.setEmail(email);
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        createdUser.setPassword(passwordEncoder.encode(password));



        User savedUser= userRepository.save(createdUser);

        cartService.createCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        String name = firstName + " " + lastName;
        AuthResponse authResponse= new AuthResponse(token,true,name, email );
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username +" ----- "+password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String token = jwtTokenProvider.generateToken(authentication);
        AuthResponse authResponse= new AuthResponse();

        authResponse.setStatus(true);
        authResponse.setJwt(token);

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("sign in userDetails - "+userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("sign in userDetails - password not match " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
