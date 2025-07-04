package fr.eni.projetspring.dal;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestValidationUtilisateur {

    @Autowired
    UtilisateurDAO utilisateurDAO;


    @Test
    public void TestReadAll() {
        System.out.println(utilisateurDAO.readById(1));
    }


    @Test
    void testPseudoUnique(){
    //vérifier qu'il n'y a aucun pseudo correspondant en base
    boolean pseudoUnique = utilisateurDAO.findByPseudo("toto35");
    assertFalse(pseudoUnique);
    }
}

