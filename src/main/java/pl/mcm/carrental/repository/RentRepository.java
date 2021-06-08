package pl.mcm.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mcm.carrental.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
