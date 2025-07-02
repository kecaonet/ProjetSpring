package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Categorie;
import fr.eni.projetspring.bo.Utilisateur;
import fr.eni.projetspring.dal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.WebListenerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    private final WebListenerRegistry webListenerRegistry;
    @Autowired
    UtilisateurDAO utilisateurDAO;

    @Autowired
    ArticleVenduDAO articleVenduDAO;

    @Autowired
    CategorieDAO categorieDAO;

    public WebController(ArticleVenduDAO articleVenduDAO, ArticleVenduDAOImpl articleVenduDAOImpl, WebListenerRegistry webListenerRegistry, UtilisateurDAOImpl utilisateurDAOImpl) {this.articleVenduDAO = articleVenduDAO;
        this.articleVenduDAO = articleVenduDAO;
        this.webListenerRegistry = webListenerRegistry;
    }

    @GetMapping("/liste")
    public String index(Model model){

    List<ArticleVendu> listeArticles = articleVenduDAO.readAllArticleVendu();
    model.addAttribute("Encheres",listeArticles);
        System.out.println(listeArticles.toString());

        List<Utilisateur> utilisateurs = utilisateurDAO.readAll();

        Map<Integer, Utilisateur> utilisateursMap = new HashMap<>();
        for (Utilisateur u : utilisateurs) {
            utilisateursMap.put(u.getNoUtilisateur(), u);
        }

    model.addAttribute("Utilisateurs", utilisateursMap);

        List<Categorie> categories = categorieDAO.readAllCategorie();

        Map<Integer, Categorie> categoriesMap = new HashMap<>();
        for (Categorie c : categories) {
            categoriesMap.put(c.getNoCategorie(), c);
        }
        model.addAttribute("Categories", categoriesMap);


        return "liste";
    }

    @GetMapping("/profil")
    public String profil(){
        return "profil";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/connex")
    public String connex(){
        return "redirect:/index";
    }

}
