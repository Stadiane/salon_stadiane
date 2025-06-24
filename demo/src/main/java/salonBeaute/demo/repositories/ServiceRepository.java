package salonBeaute.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import salonBeaute.demo.models.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
