package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bll.UtilisateurService;
import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Utilisateur;
import fr.eni.projetspring.dal.ArticleVenduDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.time.LocalDate;


@Controller
@SessionAttributes("utilisateurEnSession")
public class UtilisateurController {
    private final ArticleVenduDAOImpl articleVenduDAOImpl;
    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService, ArticleVenduDAOImpl articleVenduDAOImpl) {
        this.utilisateurService = utilisateurService;
        this.articleVenduDAOImpl = articleVenduDAOImpl;
    }

    @GetMapping("/login")
    public String login() { return "login";}

    @GetMapping("/session")
    String chargerUtilisateurEnSession(@ModelAttribute("utilisateurEnSession")Utilisateur utilisateurEnSession, Principal principal) {
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

        Utilisateur utilisateurEnSession = utilisateurService.charger(principal.getName());
        ArticleVendu articleVendu = articleVenduDAOImpl.read(noArticle);
        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("utilisateur", utilisateurEnSession);
        model.addAttribute("dateJour", LocalDate.now());
        return "modif_vente";
    }
/*
    @PostMapping("/vente")
    public String uploadVente(@ModelAttribute("utilisateur") ArticleVendu articleVendu,
                               BindingResult result, Principal principal) {
        Utilisateur utilisateurEnSession = utilisateurService.consulterUtilisateurParPseudo(principal.getName());
        System.out.println("Utilsateur connecté: " + utilisateurEnSession);
        System.out.println(utilisateur);
        utilisateur.setNoUtilisateur(utilisateurEnSession.getNoUtilisateur());
        //Si le pseudo n'est pas modifié, il est set à nulle
        if (utilisateur.getPseudo().equals(utilisateurEnSession.getPseudo())) {
            utilisateur.setPseudo(null);
        }
        //Si l'email n'est pas modifié, il est set à nulle
        if (utilisateur.getEmail().equals(utilisateurEnSession.getEmail())) {
            utilisateur.setEmail(null);
        }
        if (!result.hasErrors()) {
            try {
                utilisateurService.modifierUtilisateur(utilisateur);
                //return "redirect:/liste";
                return "redirect:/logout";
            } catch (BusinessException e) {
                System.err.println(e.getClefsExternalisations());
                e.getClefsExternalisations().forEach( key -> {
                    ObjectError error = new ObjectError("globalError", key);
                    result.addError(error);
                });
            }
        }
        System.out.println("erreur updateProfil");
        return "update_profil";
    };
*/
}
