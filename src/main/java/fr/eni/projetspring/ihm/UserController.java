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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    UtilisateurDAO utilisateurDAO;

    @Autowired
    UtilisateurController utilisateurController;

    /*@GetMapping("/updateProfil")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Utilisateur utilisateur = utilisateurDAO.readById(id);
                //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("utilisateur", utilisateur);
        return "update_profil";
    }*/

    /*@GetMapping("/updateProfil")
    public String modifProfil(Model model) {

        Utilisateur utilisateur = utilisateurService.charger(pseudo);
        model.addAttribute("Utilisateur", utilisateurEnSession);
        return "update_profil";
    }*/

        @GetMapping("/updateProfil")
        public String modifProfil(@RequestParam(name = "idParam") String pseudo, Model model) {

            Utilisateur utilisateur = utilisateurService.charger(pseudo);
            model.addAttribute("utilisateur", utilisateur);
            return "update_profil";
        }


    //   @GetMapping("/profil")
    //    public String displayProfil(@RequestParam(name = "idParam") String pseudo, Model model) {
    //
    //        Utilisateur utilisateur = utilisateurService.charger(pseudo);
    //        model.addAttribute("Utilisateur", utilisateur);
    //        return "profil";
    //    }

    @PostMapping("/update-profil")
    public String updateProfil(@ModelAttribute("utilisateur") Utilisateur utilisateur,
                               BindingResult result) {
        if (result.hasErrors()) {
            //utilisateur.setId(id);
            return "update_profil";
        }
        if (utilisateur.getMotDePasse()==null) {

               try {
                    utilisateurService.modifierUtilisateur(utilisateur);
                    return "profil";
               }
               catch (BusinessException be) {
                   System.err.println(be.getClefsExternalisations());
                                   be.getClefsExternalisations().forEach( key -> {
                                      ObjectError error = new ObjectError("globalError", key);
                                       result.addError(error);
               });
            }
        }
       return "update_profil";
    };

    //    public String createUser(@ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult) {
    //        if (!bindingResult.hasErrors()) {
    //            try {
    //                utilisateurService.ajouterUtilisateur(utilisateur);
    //                return "redirect:/liste";
    //            } catch (BusinessException e) {
    //                System.err.println(e.getClefsExternalisations());
    //                e.getClefsExternalisations().forEach( key -> {
    //                    ObjectError error = new ObjectError("globalError", key);
    //                    bindingResult.addError(error);
    //                });
    //            }
    //        }
    //        return "create_profil";







    /*@PostMapping("/update-profil")
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
        }*/

}

