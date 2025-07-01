package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {

    void create(Utilisateur utilisateur);

    void update(Utilisateur utilisateur);

    Utilisateur readById(int id);

    List<Utilisateur> readAll();

    void deleteById(int id);

    public boolean findEmail(String email);

    public boolean findPseudo(String password);

}

