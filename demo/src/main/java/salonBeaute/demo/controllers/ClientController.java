package salonBeaute.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import salonBeaute.demo.models.Client;
import salonBeaute.demo.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Afficher le formulaire d'inscription
    @GetMapping("/inscription")
    public String showInscriptionForm(Model model) {
        model.addAttribute("client", new Client());
        return "screens/creation-compte";
    }

    // Traitement du formulaire
    @PostMapping("/inscription")
    public String enregistrerClient(@ModelAttribute("client") Client client) {
        clientService.inscrire(client);
        return "redirect:/connexion"; // ou page de confirmation
    }
}
