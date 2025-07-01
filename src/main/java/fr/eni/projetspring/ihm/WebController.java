package fr.eni.projetspring.ihm;

import fr.eni.projetspring.bo.Enchere;
import fr.eni.projetspring.dal.EnchereDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    EnchereDAO enchereDAO;

    public WebController(EnchereDAO enchereDAO) {this.enchereDAO = enchereDAO;}

    @GetMapping("/index")
    public String index(Model model){
    List<Enchere> listeEnchere = enchereDAO.findAll();
    model.addAttribute("Encheres",listeEnchere);
        return "index";
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
