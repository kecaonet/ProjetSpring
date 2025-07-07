package fr.eni.projetspring.bll;

import fr.eni.projetspring.bo.Utilisateur;
import fr.eni.projetspring.dal.UtilisateurDAO;
import fr.eni.projetspring.exceptions.BusinessCode;
import fr.eni.projetspring.exceptions.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
        return utilisateurDAO.findUtilByPseudo(pseudo);
    }

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        //Validation de l'utilisateur avant sauvegarde
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validerUtilisateur(utilisateur, be);
        isValid &= validerUtilisateurUnique(utilisateur.getEmail(), utilisateur.getPseudo(), be);
        isValid &= validerPseudo(utilisateur.getPseudo(), be);

        if (isValid) {
            try {
                utilisateurDAO.create(utilisateur);
            } catch (DataAccessException e) { //Exception de la couche DAL
                //Rollback auto
                be.add(BusinessCode.BLL_UTILISATEUR_CREER_ERREUR + " " + e.getMessage());
                throw be;
            }
        } else {
            throw be;
        }
    }

// --------------------------- Début Validations User ---------------------------

    private boolean validerUtilisateur(Utilisateur u, BusinessException be) {
        if (u == null) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_NULL);
            return false;
        }
        return true;
    };

    private boolean validerUtilisateurUnique(String email, String pseudo, BusinessException be) {
        //Valider que pseudo et email sont uniques
        try {
            boolean pseudoExiste = utilisateurDAO.findByPseudo(pseudo);
            boolean emailExiste = utilisateurDAO.findByEmail(email);
            if (pseudoExiste || emailExiste) {
                if (pseudoExiste) {
                    be.add(BusinessCode.VALIDATION_PSEUDO_UNIQUE);
                }
                if (emailExiste) {
                    be.add(BusinessCode.VALIDATION_EMAIL_UNIQUE);
                }
                return false;
            }
        } catch (DataAccessException e) {
            //l'utilisateur existe déjà
            be.add(BusinessCode.VALIDATION_UTILISATEUR_UNIQUE);
            return false;
        }
        return true;
    }

    private boolean validerPseudo(String pseudo,  BusinessException be) {
        if (!pseudo.matches("^[a-zA-Z0-9]+$")) {
            be.add(BusinessCode.VALIDATION_PSEUDO_INVALIDE);
            return false;
        }
        return true;
    }

// --------------------------- Fin Validations User ---------------------------


    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) {
        utilisateurDAO.update(utilisateur);
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
}
