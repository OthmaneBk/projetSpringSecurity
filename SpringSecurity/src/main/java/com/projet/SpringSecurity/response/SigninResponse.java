package com.projet.SpringSecurity.response;

import com.projet.SpringSecurity.model.Personne;

public class SigninResponse {
    private AuthResponse authResponse;
    private Personne personne;



    public SigninResponse() {}

    public SigninResponse(AuthResponse authResponse, Personne personne) {
        this.authResponse = authResponse;
        this.personne = personne;
    }

    public AuthResponse getAuthResponse() {
        return authResponse;
    }

    public void setAuthResponse(AuthResponse authResponse) {
        this.authResponse = authResponse;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
}
