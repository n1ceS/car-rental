package pl.mcm.carrental.service;

import org.springframework.data.domain.Sort;
import pl.mcm.carrental.model.Car;
import pl.mcm.carrental.payload.ApiResponse;

import java.awt.print.Pageable;
import java.util.List;

public interface CarService {

    List<Car> getAllCars(int page, int size);

    List<Car> getAvailableCars(int page, Sort.Direction sort);

    List<String> getAllBrands(int page, Sort.Direction sort);

    List<String> getAllModelsFromBrand(String brand);

    Car addCar(Car car);

    Car editCar(Car car);

    ApiResponse deleteCar(long id);
}
