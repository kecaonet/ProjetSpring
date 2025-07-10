package fr.eni.projetspring.bll;

import fr.eni.projetspring.bo.ArticleVendu;
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

    void supprimerUtilisateur(int noUtilisateur);

    Utilisateur consulterUtilisateur (int noUtilisateur);

    Utilisateur consulterUtilisateurParPseudo(String pseudo);

    void activerUtilisateur(int noUtilisateur);

    void desactiverUtilisateur(int noUtilisateur);

    List<Utilisateur> consulterUtilisateurs();

    Utilisateur consulterCredits(Utilisateur utilisateur);

    void modifVente(ArticleVendu articleVendu);

}
