package pl.mcm.carrental.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mcm.carrental.model.Car;

import java.util.List;

@Repository
public interface CarRepository  extends JpaRepository<Car, Long> {
    List<Car> getAllByCarStatus(String status);

    @Query("Select c from Car c")
    List<Car> findAllCars(Pageable page);

    @Query("select distinct c.brand from Car c")
    List<String> getAllBrands();

    @Query("select distinct c.model from Car c where c.brand=:#{#brand}")
    List<String> getALlModelsFromBrand(@Param("brand") String brand);
}
