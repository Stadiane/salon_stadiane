package salonBeaute.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import salonBeaute.demo.models.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
}
