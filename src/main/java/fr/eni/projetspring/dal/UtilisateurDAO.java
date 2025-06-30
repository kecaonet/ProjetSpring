package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
    Utilisateur read(long id);

    List<Utilisateur> readAll();



}

