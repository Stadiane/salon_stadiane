package salonBeaute.demo.repositories;

import salonBeaute.demo.models.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    // méthodes personnalisées
}
