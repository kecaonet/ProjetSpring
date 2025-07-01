package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.ArticleVendu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestValidationArticleVendu {

    @Autowired
    ArticleVenduDAO articleVenduDAO;
    @Autowired
    UtilisateurDAO utilisateurDAO;

    @Test
    public void TestReadAll() {
        System.out.println((articleVenduDAO.readAllArticleVendu()));
        System.out.println(utilisateurDAO.readAll());
    }
    @Test
    public void TestInsert() {
        ArticleVendu articleVendu = new ArticleVendu();
        articleVendu.setNomArticle("Test");
        articleVendu.setDescription("test");
        articleVendu.setDateDebutEncheres("01/07/2025");
        articleVendu.setDateFinEncheres("10/07/2025");
        articleVendu.setPrixInitial(100);
        articleVendu.setUtilisateur(1);
        articleVendu.setCategorie(1);
    }
    @Test
    public void TestUpdate() {

    }
    @Test
    public void TestDelete() {

    }

}
