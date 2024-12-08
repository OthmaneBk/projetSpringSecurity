package com.projet.SpringSecurity.service;


import com.projet.SpringSecurity.model.Personne;

import com.projet.SpringSecurity.repository.PersonneRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImplementation implements UserDetailsService {

    @Autowired
    private PersonneRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);
        Personne user = userRepository.findByNomutilisateur(username);

        if(user==null) {
            throw new UsernameNotFoundException("User not found with this username" + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleuti()+""));
        return new org.springframework.security.core.userdetails.User(
                user.getNomutilisateur(),
                user.getMotdepasse(),
                authorities);
    }
}
