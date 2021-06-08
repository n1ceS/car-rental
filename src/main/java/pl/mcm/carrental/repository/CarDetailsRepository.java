package pl.mcm.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mcm.carrental.model.CarDetails;

public interface CarDetailsRepository extends JpaRepository<CarDetails, Long> {
}
