package com.projet.SpringSecurity.model;

import jakarta.persistence.*;

import java.text.ParseException;
import java.util.Map;

import com.projet.SpringSecurity.model.Enum.roleutilisateur;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "personne")

public class Personne {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nomutilisateur")
    private String nomutilisateur;

    @Column(name = "motdepasse")
    private String motdepasse;


    @Enumerated(EnumType.STRING)
    @Column(name = "roleuti")
    private roleutilisateur roleuti;




    public Personne(){}

    public Personne(Map<String,String> map) throws ParseException {
        this.nomutilisateur=map.get("nomutilisateur");
        this.motdepasse= map.get("motdepasse");
        this.roleuti=roleutilisateur.valueOf(map.get("roleuti"));

    }




    public String getNomutilisateur() {
        return nomutilisateur;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public int getId() {
        return id;
    }


    public String getRoleuti() {
        return roleuti+"";
    }




    public void setNomutilisateur(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }


    public void setRoleuti(roleutilisateur roleuti) {
        this.roleuti = roleuti;
    }


}
