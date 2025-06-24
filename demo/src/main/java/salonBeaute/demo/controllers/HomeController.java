package salonBeaute.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //  Page d'accueil
    @GetMapping("/")
    public String accueil() {
        return "screens/accueil";
    }

    // Page des services
    @GetMapping("/services")
    public String services() {
        return "screens/services";
    }

    // Page de connexion (juste l'affichage, le traitement est ailleurs)
    @GetMapping("/connexion")
    public String connexion() {
        return "screens/connexion";
    }

    // Page de confirmation après une action réussie
    @GetMapping("/confirmation")
    public String confirmation() {
        return "screens/confirmation";
    }
}
