package salonBeaute.demo.controllers;

import salonBeaute.demo.models.Service;
import salonBeaute.demo.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/list-services")
    public String afficherServices(Model model) {
        List<Service> services = serviceRepository.findAll();
        model.addAttribute("services", services);
        return "screens/services";
    }
}
