package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.ArticleVendu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void TestReadId() {
        System.out.println(articleVenduDAO.read(11));

    }
    @Test
    public void TestInsert() throws ParseException {
    }
    @Test
    public void TestUpdate() {

    }
    @Test
    public void TestDelete() {
        articleVenduDAO.deleteArticleVendu(7);
        System.out.println(articleVenduDAO.readAllArticleVendu());
    }

}
