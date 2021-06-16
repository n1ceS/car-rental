package pl.mcm.carrental.service;

import org.springframework.data.domain.Sort;
import pl.mcm.carrental.model.Car;
import pl.mcm.carrental.payload.ApiResponse;

import java.awt.print.Pageable;
import java.util.List;

public interface CarService {

    List<Car> getAllCars(int page, int size);

    List<Car> getAvailableCars();

    List<String> getAllBrands();

    List<String> getAllModelsFromBrand(String brand);

    Car addCar(Car car);

    Car editCar(Long id, Car car);

    ApiResponse deleteCar(Long id);

    List<Car> getCarsByStatus(String status);

    Car getCarById(Long id);
}
