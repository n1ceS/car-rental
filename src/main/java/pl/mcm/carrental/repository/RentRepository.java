package pl.mcm.carrental.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mcm.carrental.model.Rent;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    @Query("Select r from Rent r")
    List<Rent> findAllRents(Pageable pageable);
}
