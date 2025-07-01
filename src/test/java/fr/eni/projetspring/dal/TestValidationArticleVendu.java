package fr.eni.projetspring.dal;

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

}
