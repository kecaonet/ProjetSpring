package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bll.UtilisateurService;
import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Categorie;
import fr.eni.projetspring.bo.Utilisateur;
import fr.eni.projetspring.dal.ArticleVenduDAOImpl;
import fr.eni.projetspring.dal.CategorieDAO;
import fr.eni.projetspring.exceptions.BusinessException;
import fr.eni.projetspring.ihm.converter.CategorieConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@SessionAttributes("utilisateurEnSession")
public class UtilisateurController {
    private final ArticleVenduDAOImpl articleVenduDAOImpl;
    private final CategorieConverter categorieConverter;
    private UtilisateurService utilisateurService;
    private CategorieDAO categorieDAO;

    public UtilisateurController(UtilisateurService utilisateurService, ArticleVenduDAOImpl articleVenduDAOImpl, CategorieConverter categorieConverter) {
        this.utilisateurService = utilisateurService;
        this.articleVenduDAOImpl = articleVenduDAOImpl;
        this.categorieConverter = categorieConverter;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/session")
    String chargerUtilisateurEnSession(@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession, Principal principal) {
        String pseudo = principal.getName();
        Utilisateur aCharger = utilisateurService.charger(pseudo);
        if (aCharger != null) {
            utilisateurEnSession.setNoUtilisateur(aCharger.getNoUtilisateur());
            utilisateurEnSession.setPseudo(aCharger.getPseudo());
            utilisateurEnSession.setNom(aCharger.getNom());
            utilisateurEnSession.setPrenom(aCharger.getPrenom());
            utilisateurEnSession.setEmail(aCharger.getEmail());
            utilisateurEnSession.setTelephone(aCharger.getTelephone());
            utilisateurEnSession.setRue(aCharger.getRue());
            utilisateurEnSession.setCodePostal(aCharger.getCodePostal());
            utilisateurEnSession.setVille(aCharger.getVille());
            utilisateurEnSession.setCredit(aCharger.getCredit());
            utilisateurEnSession.setAdministrateur(aCharger.isAdministrateur());
        } else {
            utilisateurEnSession.setNoUtilisateur(0);
            utilisateurEnSession.setPseudo(null);
            utilisateurEnSession.setNom(null);
            utilisateurEnSession.setPrenom(null);
            utilisateurEnSession.setEmail(null);
            utilisateurEnSession.setTelephone(null);
            utilisateurEnSession.setRue(null);
            utilisateurEnSession.setCodePostal(null);
            utilisateurEnSession.setVille(null);
            utilisateurEnSession.setCredit(0);
            utilisateurEnSession.setAdministrateur(false);
        }
        System.out.println(utilisateurEnSession);

        return "redirect:/liste";
    }

    // Ajout pour utilisation UilisateurEnSession. Cette méthode met par défaut un nouveau membre en session
    @ModelAttribute("utilisateurEnSession")
    public Utilisateur utilisateurEnSession() {
        System.out.println("Add Attribut Session");
        return new Utilisateur();
    }

    @GetMapping("/modif_vente")
    public String modifVente(@RequestParam(name = "idParam") int noArticle, Model model, Principal principal) {
        System.out.println("test controller Get modif vente");

        //Categorie categorie = categorieDAO.readCategorie(articleVendu.getNocategorie());
        //Utilisateur utilisateurEnSession = utilisateurService.charger(principal.getName());
        ArticleVendu articleVendu = articleVenduDAOImpl.read(noArticle);
        model.addAttribute("articleVendu", articleVendu);
        //model.addAttribute("utilisateur", utilisateurEnSession);
        //model.addAttribute("dateJour", LocalDate.now());
        //model.addAttribute("categorie", categorie);
        return "modif_vente";
    }

    @PostMapping("/modif_vente")
    public String uploadVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu,
                              BindingResult result, Principal principal) {
        Utilisateur utilisateur = utilisateurService.consulterUtilisateurParPseudo(principal.getName());

        articleVendu.setUtilisateur(utilisateur);
        System.out.println(articleVendu);
        System.out.println(result.getAllErrors());
        System.out.println(articleVendu.getNoArticle());
        if (!result.hasErrors()) {
            try {
                utilisateurService.modifVente(articleVendu);
                //return "redirect:/liste";
                System.out.println("test Controller Post modifVente OK");
                return "redirect:/liste";
            } catch (BusinessException e) {
                System.err.println(e.getClefsExternalisations());
                e.getClefsExternalisations().forEach(key -> {
                    ObjectError error = new ObjectError("globalError", key);
                    result.addError(error);
                });
            }
        }
        System.out.println("erreur updateProfil");
        return "modif_vente";
    }

    ;

    @PostMapping("/supprimer_vente")
    public String supprimerVente(@RequestParam(value = "idParam") int noArticle) {
        utilisateurService.supprimerVente(noArticle);
        //return "redirect:/liste";
        System.out.println("test Controller Post Supp OK");
        return "redirect:/liste";
    }

    @PostMapping("/ajoutCategorie")
    public String modifCategorie(@ModelAttribute("categorie") Categorie categorie, Model model) {
        categorie.setLibelle(categorie.getLibelle());
        utilisateurService.ajouterCategorie(categorie);
        return "redirect:/liste";
    }
}
