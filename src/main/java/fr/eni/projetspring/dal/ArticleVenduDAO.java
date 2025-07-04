package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {

    void createArticleVendu(ArticleVendu articleVendu);

    ArticleVendu read(int noArticle);

    List<ArticleVendu> readAllArticleVendu();

    void updateArticleVendu(ArticleVendu articleVendu);

    void deleteArticleVendu(int noArticle);
}
