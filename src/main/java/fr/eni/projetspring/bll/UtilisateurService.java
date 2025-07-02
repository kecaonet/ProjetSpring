package fr.eni.projetspring.bll;

import fr.eni.projetspring.bo.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UtilisateurService{

    /**
     * @param pseudo
     * @return les informations de l'utilisateur identifié par son pseudo
     */
    Utilisateur charger(String pseudo);

    void ajouterUtilisateur(Utilisateur utilisateur);

    void modifierUtilisateur(int id);

    void supprimerUtilisateur(int id);

    Utilisateur consulterUtilisateur (int id);

    void desactiverUtilisateur(int id);

    List<Utilisateur> consulterUtilisateurs();

    Utilisateur consulterCredits(Utilisateur utilisateur);

}
