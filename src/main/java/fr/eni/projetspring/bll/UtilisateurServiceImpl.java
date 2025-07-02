package fr.eni.projetspring.bll;

import fr.eni.projetspring.bo.Utilisateur;
import fr.eni.projetspring.dal.UtilisateurDAO;
import fr.eni.projetspring.exceptions.BusinessCode;
import fr.eni.projetspring.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurDAO utilisateurDAO;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    /**
     * @param pseudo
     * @return les informations de l'utilisateur identifié par son pseudo
     */
    @Override
    public Utilisateur charger(String pseudo) {
        return utilisateurDAO.findByPseudo(pseudo);
    }

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        /*BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validerUtilisateur(utilisateur, be);
        isValid &= validerUilisateurUnique(utilisateur.getEmail(), utilisateur.getPseudo(), be);
        isValid &= validerPseudo(utilisateur.getPseudo(), be);
        isValid &= validerNom(utilisateur.getNom(), be);
        isValid &= validerPrenom(utilisateur.getPrenom(), be);
        isValid &= validerEmail(utilisateur.getEmail(), be);
        isValid &= validerTelephone(utilisateur.getTelephone(), be);
        isValid &= validerRue(utilisateur.getRue(), be);
        isValid &= validercodePostal(utilisateur.getCodePostal(), be);
        isValid &= validerVille(utilisateur.getVille(), be);
        isValid &= validerMotDePasse(utilisateur.getMotDePasse(),be);
        isValid &= validerCredit(utilisateur.getCredit(), be);
        isValid &= validerAdmin(utilisateur.isAdministrateur(), be);
*/
    }


    @Override
    public void modifierUtilisateur(int id) {

    }

    @Override
    public void supprimerUtilisateur(int id) {

    }

    @Override
    public Utilisateur consulterUtilisateur(int id) {
        return null;
    }

    @Override
    public void desactiverUtilisateur(int id) {

    }

    @Override
    public List<Utilisateur> consulterUtilisateurs() {
        return List.of();
    }

    @Override
    public Utilisateur consulterCredits(Utilisateur utilisateur) {
        return null;
    }

    private boolean validerUtilisateur(Utilisateur u, BusinessException be) {
        if (u == null) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_NULL);
            return false;
        }
        return true;
    };


    /*private boolean validerAdmin(boolean administrateur, BusinessException be) {
    }

    private boolean validerCredit(int credit, BusinessException be) {
    }

    private boolean validerMotDePasse(String motDePasse, BusinessException be) {
    }

    private boolean validerVille(String ville, BusinessException be) {
    }

    private boolean validercodePostal(String codePostal, BusinessException be) {
    }

    private boolean validerRue(String rue, BusinessException be) {
    }

    private boolean validerTelephone(String telephone, BusinessException be) {
    }

    private boolean validerEmail(String email, BusinessException be) {
    }

    private boolean validerPrenom(String prenom, BusinessException be) {
    }

    private boolean validerNom(String nom, BusinessException be) {
    }

    private boolean validerPseudo(String pseudo, BusinessException be) {
    }

    private boolean validerUilisateurUnique(String email, String pseudo, BusinessException be) {
        //Valider que peseudo et email sont uniques
        try {
            boolean pseudoExiste = utilisateurDAO.findPseudo(pseudo);
            boolean emailExiste = utilisateurDAO.findEmail(email);
            if (pseudoExiste && emailExiste) {
                be.add(BusinessCode.VALIDATION_UTILISATEUR_UNIQUE);
                return false;
            }
        } catch (DataAccessException e) {
            //l'utilisateur existe déjà
            be.add(BusinessCode.VALIDATION_UTILISATEUR_UNIQUE);
            return false;
        }
        return true;
    }*/

}
