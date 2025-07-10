package fr.eni.projetspring.bll;

import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Categorie;
import fr.eni.projetspring.bo.Utilisateur;
import fr.eni.projetspring.dal.ArticleVenduDAO;
import fr.eni.projetspring.dal.CategorieDAO;
import fr.eni.projetspring.dal.UtilisateurDAO;
import fr.eni.projetspring.exceptions.BusinessCode;
import fr.eni.projetspring.exceptions.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurDAO utilisateurDAO;
    private final ArticleVenduDAO articleVenduDAO;
    private final CategorieDAO categorieDAO;

    private PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder(12);

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, ArticleVenduDAO articleVenduDAO, CategorieDAO categorieDAO) {
        this.utilisateurDAO = utilisateurDAO;
        this.articleVenduDAO = articleVenduDAO;
        this.categorieDAO = categorieDAO;
    }

    /**
     * @param pseudo
     * @return les informations de l'utilisateur identifié par son pseudo
     */
    @Override
    public Utilisateur charger(String pseudo) {
        return utilisateurDAO.findUtilByPseudo(pseudo);
    }

// ================================== Création Utilisateur ==================================

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        //Encodage du mot de passe entré par l'utilisateur sur l'ihm via BCrypt
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        //Validation de l'utilisateur avant sauvegarde
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validerPseudoUnique(utilisateur.getPseudo(), be);
        isValid &= validerEmailUnique(utilisateur.getEmail(), be);
        isValid &= validerPseudo(utilisateur.getPseudo(), be);
        utilisateur.setDesactive(true);

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

// ================================ Modification Utilisateur ================================

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) {
        //Vérif de la présence d'un nouveau mot de passe
        if (!utilisateur.getMotDePasse().isEmpty()) {
            //Encodage du mot de passe entré par l'utilisateur sur l'ihm via BCrypt
            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        }
        //Validation de l'utilisateur avant modification
        BusinessException be = new BusinessException();
        boolean isValid = true;
        //Si le pseudo a été modifié, rajout validation d'unicité et regex sur ce dernier
        if (utilisateur.getPseudo() != null) {
            isValid &= validerPseudoUnique(utilisateur.getPseudo(), be);
            isValid &= validerPseudo(utilisateur.getPseudo(), be);
        }
        //Si l'email a été modifié, rajout validation d'unicité sur ce dernier
        if (utilisateur.getEmail() != null) {
            isValid &= validerEmailUnique(utilisateur.getEmail(), be);
        }
        if (isValid) {
            try {
                utilisateurDAO.update(utilisateur);
            } catch (DataAccessException e) { //Exception de la couche DAL
                //Rollback auto
                be.add(BusinessCode.BLL_UTILISATEUR_UPDATE_ERREUR + " " + e.getMessage());
                throw be;
            }
        }
    }

// ================================= Suppression Utilisateur =================================

    @Override
    public void supprimerUtilisateur(int noUtilisateur) {
        utilisateurDAO.deleteById(noUtilisateur);
    }

// ================================== Lecture Utilisateur ==================================

    @Override
    public Utilisateur consulterUtilisateur(int noUtilisateur) {
        return utilisateurDAO.readById(noUtilisateur);
    }

    @Override
    public Utilisateur consulterUtilisateurParPseudo(String pseudo) {
        return utilisateurDAO.findUtilByPseudo(pseudo);
    }

// ================================ Activation Utilisateur ================================

    @Override
    public void activerUtilisateur(int noUtilisateur) {
        utilisateurDAO.desactivateById(noUtilisateur,true);
    }

// ================================ Désactivation Utilisateur ================================

    @Override
    public void desactiverUtilisateur(int noUtilisateur) {
        utilisateurDAO.desactivateById(noUtilisateur,false);
    }

// ========================== Lecture de tous les utilisateurs non admin ==========================

    @Override
    public List<Utilisateur> consulterUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        for (Utilisateur u : utilisateurDAO.readAll() ) {
            System.out.println("Utilisateur: " + u);
            System.out.println("isDesactive: " + u.isDesactive());
            if (!u.isAdministrateur()) {
                utilisateurs.add(u);
            }
        }
        return utilisateurs;
    }

// ======================================= Lecture Crédits =======================================

    @Override
    public Utilisateur consulterCredits(Utilisateur utilisateur) {
        return null;
    }

    // ======================================= Modifs Ventes  =======================================

    @Override
    public void modifVente(ArticleVendu articleVendu) {
        BusinessException be = new BusinessException();
            try {
                articleVenduDAO.updateArticleVendu(articleVendu);
                System.out.println("test modif Article Vendu: " + articleVendu);
            } catch (DataAccessException e) { //Exception de la couche DAL
                //Rollback auto
                be.add(BusinessCode.BLL_VENTE_UPDATE_ERREUR + " " + e.getMessage());
                throw be;
            }
    }
    @Override
    public void supprimerVente(int id) {
        BusinessException be = new BusinessException();
        try {
            articleVenduDAO.deleteArticleVendu(id);
            System.out.println("test suppression Article Vendu");
        } catch (DataAccessException e) { //Exception de la couche DAL
            //Rollback auto
            be.add(BusinessCode.BLL_VENTE_UPDATE_ERREUR + " " + e.getMessage());
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

    private boolean validerPseudoUnique(String pseudo, BusinessException be) {
        //Valider que le pseudo est unique
        try {
            boolean pseudoExiste = utilisateurDAO.findByPseudo(pseudo);
            if (pseudoExiste) {
                be.add(BusinessCode.VALIDATION_PSEUDO_UNIQUE);
                return false;
            }
        } catch (DataAccessException e) {
            //l'utilisateur existe déjà
            be.add(BusinessCode.VALIDATION_PSEUDO_UNIQUE);
            return false;
        }
        return true;
    }

    private boolean validerEmailUnique(String email, BusinessException be) {
        //Valider que l'email est unique
        try {
            boolean emailExiste = utilisateurDAO.findByEmail(email);
            if (emailExiste) {
                be.add(BusinessCode.VALIDATION_EMAIL_UNIQUE);
                return false;
            }
        } catch (DataAccessException e) {
            //l'utilisateur existe déjà
            be.add(BusinessCode.VALIDATION_EMAIL_UNIQUE);
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

    public void ajouterCategorie(Categorie categorie) {
        categorieDAO.createCategorie(categorie);
    }

}
