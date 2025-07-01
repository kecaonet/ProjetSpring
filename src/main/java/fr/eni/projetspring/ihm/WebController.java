package fr.eni.projetspring.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/index")
    public String index(){

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
