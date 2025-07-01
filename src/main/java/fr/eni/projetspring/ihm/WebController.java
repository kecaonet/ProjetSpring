package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Enchere;
import fr.eni.projetspring.dal.ArticleVenduDAO;
import fr.eni.projetspring.dal.ArticleVenduDAOImpl;
import fr.eni.projetspring.dal.EnchereDAO;
import org.springframework.boot.web.servlet.WebListenerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    private final WebListenerRegistry webListenerRegistry;
    ArticleVenduDAO articleVenduDAO;

    public WebController(ArticleVenduDAO articleVenduDAO, ArticleVenduDAOImpl articleVenduDAOImpl, WebListenerRegistry webListenerRegistry) {this.articleVenduDAO = articleVenduDAO;
        this.articleVenduDAO = articleVenduDAO;
        this.webListenerRegistry = webListenerRegistry;
    }

    @GetMapping("/liste")
    public String index(Model model){
    List<ArticleVendu> listeArticles = articleVenduDAO.readAllArticleVendu();
    model.addAttribute("Encheres",listeArticles);
        System.out.println(listeArticles.toString());
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
