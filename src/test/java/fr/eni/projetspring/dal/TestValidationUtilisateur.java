package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Enchere;
import fr.eni.projetspring.bo.Utilisateur;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestValidationUtilisateur {

    @Autowired
    UtilisateurDAO utilisateurDAO;
    @Autowired
    EnchereDAO enchereDAO;


    @Test
    public void TestReadAll() {
        System.out.println(utilisateurDAO.readAll());
    }

    @Test
    public void TestReadById() {
        System.out.println(utilisateurDAO.readById(5));
    }
    @Test
    public void TestEnchereList() {
        Enchere enchere = new Enchere();
        enchere.setMontantEnchere(12);
        enchere.setUtilisateur(5);
        enchere.setDateEnchere(LocalDate.of(2025,7,9));
        enchere.setArticleVendu(11);
        Utilisateur util = utilisateurDAO.readById(5);
        util.setEnchereList(enchere);
        System.out.println(util.getEnchereList());
        System.out.println(util);
        System.out.println(util.getEnchereList().get(0).getNoEnchere());
        System.out.println(util.getEnchereList().get(0));
        enchereDAO.delete(util.getEnchereList().get(0).getUtilisateur(),util.getEnchereList().get(0));
        System.out.println(util.getEnchereList());

    }


    @Test
    void testPseudoUnique(){
    //vérifier qu'il n'y a aucun pseudo correspondant en base
    boolean pseudoUnique = utilisateurDAO.findByPseudo("toto35");
    assertFalse(pseudoUnique);
    }
}

