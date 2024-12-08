package com.projet.SpringSecurity.controller;
import com.projet.SpringSecurity.SecurityConfig.JwtProvider;
import com.projet.SpringSecurity.model.Personne;
import com.projet.SpringSecurity.repository.PersonneRepo;
import com.projet.SpringSecurity.response.AuthResponse;
import com.projet.SpringSecurity.response.SigninResponse;
import com.projet.SpringSecurity.service.ServicePersonne;
import com.projet.SpringSecurity.service.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/test")
@ResponseBody
@RequiredArgsConstructor
@EnableMethodSecurity
public class Controller {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImplementation customUserDetails;

    @Autowired
    private ServicePersonne servicePersonne;

    @Autowired
    private PersonneRepo personneRepo;




    @PostMapping("/SignUp")
    //ResponseEntity<AuthResponse>
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Map<String, String> payload) throws Exception {

            Personne user = new Personne(payload);
            return servicePersonne.CreateClient(user);

    }

    @PostMapping("/Login")
    public SigninResponse signin(@RequestBody Map<String, String> payload) {
        String Username = payload.get("username");
        String password = payload.get("pwd");


        Authentication authentication = authenticate(Username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login success");
        authResponse.setJwt(token);
        //System.out.println(token);
        authResponse.setStatus(true);
        Personne personne = servicePersonne.user(Username, password);

        return new ResponseEntity<>(new SigninResponse(authResponse, personne), HttpStatus.OK).getBody();
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if (userDetails == null) {
            System.out.println("Sign in details - null" + userDetails);
            throw new BadCredentialsException("Invalid username and password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("Sign in userDetails - password mismatch" + userDetails);
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @GetMapping("/User")
    public String user() {
        return "User Page";
    }

    @GetMapping("/Admin")
    public String Admin() {
        return "User Admin";
    }
}