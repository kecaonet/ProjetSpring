package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bll.UtilisateurService;
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

@Controller
public class UserController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/updateProfil")
    public String modifProfil(@RequestParam(name = "idParam") String pseudo, Model model) {

        Utilisateur utilisateur = utilisateurService.charger(pseudo);
        model.addAttribute("utilisateur", utilisateur);
        return "update_profil";
    }

    @PostMapping("/update-profil")
    public String updateProfil(@ModelAttribute("utilisateur") Utilisateur utilisateur,
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
}

