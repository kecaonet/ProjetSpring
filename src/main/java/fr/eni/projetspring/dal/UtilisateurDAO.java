package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {

    void create(Utilisateur utilisateur);

    void update(Utilisateur utilisateur);

    Utilisateur readById(int noUtilisateur);

    List<Utilisateur> readAll();

    void deleteById(int noUtilisateur);

    public boolean findByEmail(String email);

    public boolean findByPseudo(String pseudo);

    /**
     * @param pseudo le pseudo
     * @return l'utilisateur en fonction de son pseudo
     */
    public Utilisateur findUtilByPseudo(String pseudo);

}

