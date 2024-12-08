package com.projet.SpringSecurity.repository;

import com.projet.SpringSecurity.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PersonneRepo extends JpaRepository<Personne,Integer> {

    Personne findByNomutilisateur(@Param("nom_utilisateur") String nom_utilisateur);

}
