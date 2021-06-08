package pl.mcm.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mcm.carrental.model.CarStatus;

public interface CarStatusRepository extends JpaRepository<CarStatus, String> {
}
