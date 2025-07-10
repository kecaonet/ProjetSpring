package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bll.UtilisateurService;
import fr.eni.projetspring.bo.Utilisateur;
import fr.eni.projetspring.dal.UtilisateurDAO;
import fr.eni.projetspring.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateProfilController {

    @Autowired
    UtilisateurService utilisateurService;

    public CreateProfilController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    //Récupérations des infos du formulaire html
    @GetMapping("/createProfil")
    public String createUser(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "create_profil";
    }

    @PostMapping("/createProfil")
    public String createUser(@ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                utilisateurService.ajouterUtilisateur(utilisateur);
                return "redirect:/liste";
            } catch (BusinessException e) {
                System.err.println(e.getClefsExternalisations());
                e.getClefsExternalisations().forEach(key -> {
                    ObjectError error = new ObjectError("globalError", key);
                    bindingResult.addError(error);
                });
            }
        }
        return "create_profil";
    }

}
