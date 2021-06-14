package pl.mcm.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mcm.carrental.model.RentStatus;

public interface RentStatusRepository extends JpaRepository<RentStatus, String> {
}
