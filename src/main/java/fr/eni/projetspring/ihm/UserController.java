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
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    UtilisateurDAO utilisateurDAO;

    /*@GetMapping("/updateProfil")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Utilisateur utilisateur = utilisateurDAO.readById(id);
                //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("utilisateur", utilisateur);
        return "update_profil";
    }*/

    @GetMapping("/updateProfil")
    public String modifProfil(@RequestParam(name = "idParam") String pseudo, Model model) {

        Utilisateur utilisateur = utilisateurService.charger(pseudo);
        model.addAttribute("Utilisateur", utilisateur);
        return "update_profil";
    }


    @PostMapping("/update-profil")
    public String updateProfil(@PathVariable("id") long id, Utilisateur utilisateur,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            //utilisateur.setId(id);
            return "update-user";
        }

        utilisateurDAO.update(utilisateur);
        return "redirect:/profil";
    }


    @PostMapping("/update-profil")
    public String updateProfil(@ModelAttribute("utilisateur") Utilisateur utilisateur,
                               BindingResult bindingResult) {
        String newMdp;
        String confirmMdp;

        if (bindingResult.hasErrors()) {
            return "update_profil";
        }
        try {
            System.out.println(utilisateur.toString());
            utilisateurService.modifierUtilisateur(utilisateur.getNoUtilisateur());

            return "redirect:/profil";

        }catch (BusinessException be) {
            be.getClefsExternalisations().forEach(
                    key -> {
                        ObjectError error = new ObjectError("globalError", key);
                        bindingResult.addError(error);
                    }
            );
            return "update_profil";
        }
    }
}
