package fr.eni.projetspring.bll;

import fr.eni.projetspring.bo.Utilisateur;

import java.util.List;

public interface UtilisateurService{

    /**
     * @param pseudo
     * @return les informations de l'utilisateur identifié par son pseudo
     */
    Utilisateur charger(String pseudo);

    void ajouterUtilisateur(Utilisateur utilisateur);

    void modifierUtilisateur(Utilisateur utilisateur);

    void supprimerUtilisateur(int id);

    Utilisateur consulterUtilisateur (int id);

    Utilisateur consulterUtilisateurParPseudo(String pseudo);

    void desactiverUtilisateur(int id);

    List<Utilisateur> consulterUtilisateurs();

    Utilisateur consulterCredits(Utilisateur utilisateur);

}
