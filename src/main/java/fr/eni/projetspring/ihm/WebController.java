package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bll.UtilisateurService;
import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Categorie;
import fr.eni.projetspring.bo.Enchere;
import fr.eni.projetspring.bo.Utilisateur;
import fr.eni.projetspring.dal.*;
import fr.eni.projetspring.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.WebListenerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    UtilisateurDAO utilisateurDAO;
    @Autowired
    ArticleVenduDAO articleVenduDAO;
    @Autowired
    CategorieDAO categorieDAO;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    EnchereDAO enchereDAO;


    public WebController(ArticleVenduDAO articleVenduDAO, ArticleVenduDAOImpl articleVenduDAOImpl, WebListenerRegistry webListenerRegistry, UtilisateurDAOImpl utilisateurDAOImpl) {
        this.articleVenduDAO = articleVenduDAO;
        this.categorieDAO = categorieDAO;
        this.utilisateurDAO = utilisateurDAO;
    }

    @GetMapping("/")
    public String Racine() {
        return "redirect:/liste";
    }

    @GetMapping("/liste")
    public String index(Model model) {

        List<ArticleVendu> listeArticles = articleVenduDAO.readAllArticleVendu();
        model.addAttribute("Encheres", listeArticles);
        System.out.println(listeArticles.toString());

        return "/liste";
    }

    @GetMapping("/details")
    public String displayVenteDetails(@RequestParam(name = "idParam") int idValue, Model model) {
        //récupération de la vente dont l'id est passé en paramètre
        ArticleVendu articleVendu = articleVenduDAO.read(idValue);

        model.addAttribute("ArticleVente", articleVendu);

        return "vente_details";
    }

    @GetMapping("/profil")
    public String displayProfil(@RequestParam(name = "idParam") String pseudo, Model model) {

        Utilisateur utilisateur = utilisateurService.charger(pseudo);
        model.addAttribute("Utilisateur", utilisateur);
        return "profil";
    }

    @GetMapping("/nouvelle_vente")
    public String nouvelleVente(@RequestParam(name = "idParam") String pseudo, Model model) {
        Utilisateur utilisateur = utilisateurService.charger(pseudo);
        model.addAttribute("utilisateur", utilisateur);

        List<Categorie> categorieList = categorieDAO.readAllCategorie();
        model.addAttribute("categories", categorieList);

        return "nouvelle_vente";

    }

    @PostMapping("/nouvelle-vente")
    public String createVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu, BindingResult bindingResult, Principal principal) {
        System.out.println("Entrée create Vente");
        int noCategorie = articleVendu.getCategorie().getNoCategorie();
        Categorie categorie = categorieDAO.readCategorie(noCategorie);
        Utilisateur utilisateurEnSession = utilisateurService.consulterUtilisateurParPseudo(principal.getName());
        articleVendu.setCategorie(categorie);
        articleVendu.setUtilisateur(utilisateurEnSession);
        System.out.println(bindingResult.hasErrors());
        System.out.println(bindingResult.getAllErrors());
        System.out.println(bindingResult.getTarget());
        System.out.println(bindingResult.getGlobalErrors());

        if (!bindingResult.hasErrors()) {
            try {
                System.out.println("Post Article");
                articleVenduDAO.createArticleVendu(articleVendu);
                return "redirect:/liste";
            } catch (BusinessException e) {
                System.out.println("Erreur");
                System.err.println(e.getClefsExternalisations());
                e.getClefsExternalisations().forEach(key -> {
                    ObjectError error = new ObjectError("globalError", key);
                    bindingResult.addError(error);
                });
            }
        }
        System.out.println("Return liste");
        return "liste";
    }

    @PostMapping("/nouvelle_enchere")
    public String nouvelleEnchere(@ModelAttribute("Enchere") Enchere enchereMod, BindingResult bindingResult, Principal principal) {
        System.out.println("Entree nouvelle enchère");
        Utilisateur utilisateurEnSession = utilisateurService.consulterUtilisateurParPseudo(principal.getName());

        enchereMod.setMontantEnchere(enchereMod.getMontantEnchere());
        enchereMod.setDateEnchere(LocalDate.now());
        System.out.println(enchereMod.getArticleVendu());
        enchereMod.setArticleVendu((enchereMod.getArticleVendu()));
        System.out.println("Set Utilisateur : ");
        System.out.println(utilisateurEnSession);
        enchereMod.setUtilisateur(utilisateurEnSession);
        System.out.println(bindingResult.getAllErrors());
        System.out.println(enchereMod);
        if (!bindingResult.hasErrors()) {
            try {
                System.out.println("Post Enchere");
                enchereDAO.create(enchereMod);
                utilisateurEnSession.setEnchereList(enchereMod);

            } catch (BusinessException e) {
                System.out.println("Erreur");
                System.err.println(e.getClefsExternalisations());
                e.getClefsExternalisations().forEach(key -> {
                    ObjectError error = new ObjectError("globalError", key);
                    bindingResult.addError(error);
                });
            }
            return "redirect:/liste";
        }
        return "liste";
    }
}

