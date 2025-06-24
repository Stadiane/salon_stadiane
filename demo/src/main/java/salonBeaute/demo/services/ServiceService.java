package salonBeaute.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salonBeaute.demo.repositories.ServiceRepository;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<salonBeaute.demo.models.Service> getAllServices() {
        return serviceRepository.findAll();
    }
}
