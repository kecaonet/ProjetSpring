package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.ArticleVendu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO{
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
