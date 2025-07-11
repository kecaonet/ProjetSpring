package fr.eni.projetspring.bll;

import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Categorie;
import fr.eni.projetspring.bo.Enchere;

import java.util.List;

public interface VenteService {

    ArticleVendu lireArticleVendu(int noArticle);

    List<ArticleVendu> listerArticleVendu();

    void creerArticleVendu(ArticleVendu articleVendu);

    void modifVente(ArticleVendu articleVendu);

    void supprimerVente(int id);

    void ajouterCategorie(Categorie categorie);

    List<Categorie> listerCategorie();

    Categorie lireCategorie(int noCategorie);

    void creerEnchere(Enchere enchere);

}
