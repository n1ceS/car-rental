package pl.mcm.carrental.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mcm.carrental.model.Rent;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {
    @Query("Select r from Rent r")
    List<Rent> findAllRents(Pageable pageable);

    @Query("select r from Rent r where r.rentStatus.rentStatus=:#{#status} and r.userID =:#{#userId}")
    List<Rent> findAllByRentStatusAndUserID(String status, Long userId);

    List<Rent> findAllByUserID(Long userId, Pageable pageable);
}
