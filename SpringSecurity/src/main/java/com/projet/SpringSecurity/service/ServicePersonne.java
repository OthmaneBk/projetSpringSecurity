package com.projet.SpringSecurity.service;

import com.projet.SpringSecurity.SecurityConfig.JwtProvider;
import com.projet.SpringSecurity.model.Personne;
import com.projet.SpringSecurity.repository.PersonneRepo;
import com.projet.SpringSecurity.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class ServicePersonne {

    @Autowired
    private PersonneRepo personneRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Personne user(String username, String pwd){
        Personne user2=personneRepo.findByNomutilisateur(username);
        if(user2 != null){
            if(passwordEncoder.matches(pwd,user2.getMotdepasse())){
                return user2;
            }
        }
        return null;
    }



    public String generateNewToken() {
        SecureRandom secureRandom = new SecureRandom(); // threadsafe
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public Personne findByNomutilisateur(String nom_utilisateur){
        return personneRepo.findByNomutilisateur(nom_utilisateur);
    }

    public void addUser(Personne user) throws Exception {
        user.setMotdepasse(passwordEncoder.encode(user.getMotdepasse()));
        personneRepo.save(user);
    }

    public ResponseEntity<AuthResponse> CreateClient(Personne user) throws Exception {
        Personne isEmailExist = findByNomutilisateur(user.getNomutilisateur());
        if(isEmailExist != null){
            throw new Exception("Username Is Already Used With Another Account");
        }
        addUser(user);


        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getNomutilisateur(),user.getMotdepasse());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setStatus(true);
        System.out.println(authResponse.getJwt());
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }
}
