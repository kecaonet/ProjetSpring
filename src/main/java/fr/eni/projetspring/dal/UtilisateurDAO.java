package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {

    void create(Utilisateur utilisateur);

    void update(Utilisateur utilisateur);

    Utilisateur readById(int id);

    List<Utilisateur> readAll();

    void deleteById(int id);

    public boolean findByEmail(String email);

    /**
     * @param pseudo le pseudo
     * @return l'utilisateur en fonction de son pseudo
     */
    public Utilisateur findByPseudo(String pseudo);

}

