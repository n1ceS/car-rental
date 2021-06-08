package pl.mcm.carrental.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mcm.carrental.exception.ResourceNotFoundException;
import pl.mcm.carrental.model.Car;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.repository.CarRepository;
import pl.mcm.carrental.service.CarService;
import pl.mcm.carrental.utils.ConstantAppValues;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> getAllCars(int page, int size) {
        return carRepository.findAllCars(PageRequest.of(page, size, Sort.by(ConstantAppValues.DEFAULT_SORT_DIRECTION, "id")));
    }

    @Override
    public List<Car> getAvailableCars(int page, Sort.Direction sort) {
         return carRepository.getAllByCarStatus("READY");
    }

    @Override
    public List<String> getAllBrands(int page, Sort.Direction sort) {
        return carRepository.getAllBrands();
    }

    @Override
    public List<String> getAllModelsFromBrand(String brand) {
        String convertedBrand = brand.toLowerCase();
        return carRepository.getALlModelsFromBrand(convertedBrand);
    }

    @Override
    @Transactional
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public Car editCar(Car car) {
        Car carToEdit = carRepository.findById(car.getId()).orElseThrow(() -> new ResourceNotFoundException("car", "id", car.getId()));
        carToEdit.setBrand(car.getBrand());
        carToEdit.setModel(car.getModel());
        carToEdit.setPrice(car.getPrice());
        carToEdit.setCarStatus(car.getCarStatus());
        carToEdit.setCarDetails(car.getCarDetails());
        return carToEdit;
    }

    @Override
    public ApiResponse deleteCar(long id) {
        carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("car", "id", id));
        carRepository.deleteById(id);
        return new ApiResponse(Boolean.TRUE, "You successfully deleted car");
    }
}