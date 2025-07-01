package fr.eni.projetspring.bll;

import fr.eni.projetspring.bo.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    void ajouterUtilisateur(Utilisateur utilisateur);
    void modifierUtilisateur(int id);
    void supprimerUtilisateur(int id);
    Utilisateur consulterUtilisateur (int id);
    void desactiverUtilisateur(int id);
    List<Utilisateur> consulterUtilisateurs();
    Utilisateur consulterCredits(Utilisateur utilisateur);

}
