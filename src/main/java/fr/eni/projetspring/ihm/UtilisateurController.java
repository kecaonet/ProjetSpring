package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bll.UtilisateurService;
import fr.eni.projetspring.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;


@Controller
@SessionAttributes("utilisateurEnSession")
public class UtilisateurController {
    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
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

}
