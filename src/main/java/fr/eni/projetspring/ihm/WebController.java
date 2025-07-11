package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bll.UtilisateurService;
import fr.eni.projetspring.bll.VenteService;
import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Categorie;
import fr.eni.projetspring.bo.Enchere;
import fr.eni.projetspring.bo.Utilisateur;
import fr.eni.projetspring.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
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
    UtilisateurService utilisateurService;
    @Autowired
    VenteService venteService;


    public WebController(UtilisateurService utilisateurService, VenteService venteService) {
        this.utilisateurService = utilisateurService;
        this.venteService = venteService;
    }

    @GetMapping("/")
    public String Racine() {
        return "redirect:/liste";
    }

    @GetMapping("/liste")
    public String index(Model model) {
        model.addAttribute("date", LocalDate.now());
        List<ArticleVendu> listeArticles = venteService.listerArticleVendu();
        List<Categorie> CategListe = venteService.listerCategorie();
        model.addAttribute("categories", CategListe);
        model.addAttribute("Encheres", listeArticles);
        System.out.println(listeArticles.toString());

        return "/liste";
    }

    @GetMapping("/details")
    public String displayVenteDetails(@RequestParam(name = "idParam") int idValue, Model model) {
        //récupération de la vente dont l'id est passé en paramètre
        ArticleVendu articleVendu = venteService.lireArticleVendu(idValue);
        model.addAttribute("date", LocalDate.now());
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
        List<Categorie> categorieList = venteService.listerCategorie();
        model.addAttribute("categories", categorieList);
        return "nouvelle_vente";
    }

    @PostMapping("/nouvelle-vente")
    public String createVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu, BindingResult bindingResult, Principal principal) {
        int noCategorie = articleVendu.getCategorie().getNoCategorie();
        Categorie categorie = venteService.lireCategorie(noCategorie);
        Utilisateur utilisateurEnSession = utilisateurService.consulterUtilisateurParPseudo(principal.getName());
        articleVendu.setCategorie(categorie);
        articleVendu.setDateDebutEncheres(articleVendu.getDateDebutEncheres());
        articleVendu.setDateFinEncheres(articleVendu.getDateFinEncheres());
        articleVendu.setUtilisateur(utilisateurEnSession);
        articleVendu.setLieuRetrait(articleVendu.getLieuRetrait());
        System.out.println(bindingResult.hasErrors());
        System.out.println(bindingResult.getAllErrors());
        System.out.println(bindingResult.getTarget());
        System.out.println(bindingResult.getGlobalErrors());

        if (!bindingResult.hasErrors()) {
            try {
                System.out.println("Post Article");
                venteService.creerArticleVendu(articleVendu);
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
        enchereMod.setUtilisateur(utilisateurEnSession.getNoUtilisateur());
        System.out.println(bindingResult.getAllErrors());
        System.out.println(enchereMod);
        if (!bindingResult.hasErrors()) {
            try {
                System.out.println("Post Enchere");
                venteService.creerEnchere(enchereMod);

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

