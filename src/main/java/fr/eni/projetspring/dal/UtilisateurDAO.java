package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {

    void create(Utilisateur utilisateur);

    Utilisateur readById(int id);

    List<Utilisateur> readAll();

    void update(int id);

    void delete(int id);

}

