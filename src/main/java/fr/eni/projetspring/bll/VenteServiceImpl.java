package fr.eni.projetspring.bll;

import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Categorie;
import fr.eni.projetspring.bo.Enchere;
import fr.eni.projetspring.dal.ArticleVenduDAO;
import fr.eni.projetspring.dal.CategorieDAO;
import fr.eni.projetspring.dal.EnchereDAO;
import fr.eni.projetspring.exceptions.BusinessCode;
import fr.eni.projetspring.exceptions.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenteServiceImpl implements VenteService {

    private final ArticleVenduDAO articleVenduDAO;
    private final CategorieDAO categorieDAO;
    public final EnchereDAO enchereDAO;

    public VenteServiceImpl(ArticleVenduDAO articleVenduDAO, CategorieDAO categorieDAO, EnchereDAO enchereDAO) {
        this.articleVenduDAO = articleVenduDAO;
        this.categorieDAO = categorieDAO;
        this.enchereDAO = enchereDAO;
    }

    // ===================================== Services Articles =====================================

    @Override
    public ArticleVendu lireArticleVendu(int noArticle) {
        return articleVenduDAO.read(noArticle);
    }

    @Override
    public List<ArticleVendu> listerArticleVendu() {
        return articleVenduDAO.readAllArticleVendu();
    }

    @Override
    public void creerArticleVendu(ArticleVendu articleVendu) {
        articleVenduDAO.createArticleVendu(articleVendu);
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

    // ===================================== Services Catégorie =====================================

    @Override
    public void ajouterCategorie(Categorie categorie) {
        categorieDAO.createCategorie(categorie);
    }

    @Override
    public List<Categorie> listerCategorie() {
        return categorieDAO.readAllCategorie();
    }

    @Override
    public Categorie lireCategorie(int noCategorie) {
        return categorieDAO.readCategorie(noCategorie);
    }

    // ===================================== Services Enchere =====================================

    @Override
    public void creerEnchere(Enchere enchere) {
        enchereDAO.create(enchere);
    }
}
