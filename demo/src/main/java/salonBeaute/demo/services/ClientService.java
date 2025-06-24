package salonBeaute.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salonBeaute.demo.models.Client;
import salonBeaute.demo.models.RendezVous;
import salonBeaute.demo.repositories.ClientRepository;
import salonBeaute.demo.repositories.RendezVousRepository;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RendezVousRepository rendezVousRepository;

    // S'inscrire
    public Client inscrire(Client client) {
        return clientRepository.save(client);
    }

    // Se connecter
    public Optional<Client> seConnecter(String email, String motDePasse) {
        return clientRepository.findByEmail(email)
                .filter(c -> c.getMotDePasse().equals(motDePasse));
    }

    // Prendre rendez-vous
    public RendezVous prendreRendezVous(Long clientId, RendezVous rdv) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        rdv.setClient(client);
        return rendezVousRepository.save(rdv);
    }

    // Annuler un rendez-vous
    public void annulerRendezVous(Long rdvId) {
        rendezVousRepository.deleteById(rdvId);
    }
}
