package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.ArticleVendu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO{

    //Pas fini
    private final String INSERT = "INSERT INTO ARTICLEVENDU(NOM_ARTICLE, DESCRIPTION, DATE_DEBUT_ENCHERES, DATE_FIN_ENCHERES, PRIX_INITIAL, PRIX_VENTE, NO_UTILISATEUR, NO_CATEGORIE) " +
            "VALUES (:nomArticle, :description, :dateDebutEncheres, :dateFinEncheres, :prixInitial, :prixVente, :)";

    //à compléter
    private final String READ_BY_ID = "";

    //à compléter
    private final String READ_ALL = "";

    //à compléter
    private final String UPDATE = "";

    //à compléter
    private final String DELETE = "";



    @Override
    public void createArticleVendu(ArticleVendu articleVendu) {

    }

    @Override
    public ArticleVendu read(int noArticle) {
        return null;
    }

    @Override
    public List<ArticleVendu> readAllArticleVendu() {
        return List.of();
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {

    }

    @Override
    public void deleteArticleVendu(int noArticle) {

    }
}
