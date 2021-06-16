package pl.mcm.carrental.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.mcm.carrental.dto.CarDTO;
import pl.mcm.carrental.model.Car;
import pl.mcm.carrental.model.CarDetails;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.service.CarDetailsService;
import pl.mcm.carrental.service.CarService;
import pl.mcm.carrental.service.CarStatusService;
import pl.mcm.carrental.utils.ConstantAppValues;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    private final CarDetailsService carDetailsService;

    private final CarStatusService carStatusService;

    public CarController(CarService carService, CarDetailsService carDetailsService, CarStatusService carStatusService) {
        this.carService = carService;
        this.carDetailsService = carDetailsService;
        this.carStatusService = carStatusService;
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars(
            @RequestParam(value = "page", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE_SIZE) Integer size) {
            List<Car> cars = carService.getAllCars(page, size);

            return new ResponseEntity<>(cars.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CarDTO>> getCarsByStatus(@PathVariable(name = "status") String status) {
        List<Car> cars = carService.getCarsByStatus(status);
        return new ResponseEntity<>(cars.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<CarDTO>> getAvailableCars() {
        List<Car> availableCars = carService.getAvailableCars();
        return new ResponseEntity<>(availableCars.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<String>> getAllBrands() {
        List<String> brands= carService.getAllBrands();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{brand}/models")
    public ResponseEntity<List<String>> getAllModelsFromBrand(@PathVariable String brand) {
        List<String> allModelsFromBrand = carService.getAllModelsFromBrand(brand);
        return new ResponseEntity<>(allModelsFromBrand, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) throws ParseException {
        carStatusService.getCarStatusById(carDTO.getStatus());
        Car car = convertToEntity(carDTO);
        car.setBrand(car.getBrand().toLowerCase());
        carService.addCar(car);
        carDetailsService.addCarDetails(getCarDetailsFromCarDTO(car.getId(), carDTO));

        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> editCar(@PathVariable(name = "id") Long id, @RequestBody CarDTO carDTO) throws ParseException {
        Car carToEdit = convertToEntity(carDTO);
        carToEdit.setCarDetails(getCarDetailsFromCarDTO(id ,carDTO));
        carToEdit.setId(carToEdit.getId());
        Car carEdited = carService.editCar(id, carToEdit);
        return new ResponseEntity<>(convertToDto(carEdited), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCar(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(carService.deleteCar(id), HttpStatus.OK);
    }
    private CarDTO convertToDto(Car car) {
        CarDTO carDTO = new CarDTO();
        CarDetails carDetails = carDetailsService.getDetailsByCarId(car.getId());
        carDTO.setId(car.getId());
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setPrice(car.getPrice());
        carDTO.setStatus(car.getCarStatus());
        carDTO.setType(carDetails.getType());
        carDTO.setYear(carDetails.getYear());
        carDTO.setFuelType(carDetails.getFuelType());
        carDTO.setPower(carDetails.getPower());
        carDTO.setGearbox(carDetails.getGearbox());
        carDTO.setColor(carDetails.getColor());
        carDTO.setPhoto(carDetails.getPhoto());
        carDTO.setDescription(carDetails.getDescription());
        carDTO.setDoorsNumber(carDetails.getDoorsNumber());
        return carDTO;
    }

    private Car convertToEntity(CarDTO carDTO) throws ParseException {
        Car car = new Car();
        car.setModel(carDTO.getModel());
        car.setBrand(carDTO.getBrand());
        car.setPrice(carDTO.getPrice());
        car.setCarStatus(carDTO.getStatus());
        car.setId(carDTO.getId());
        return car;
    }
    private CarDetails getCarDetailsFromCarDTO(Long carId, CarDTO carDTO) {
        return CarDetails.builder()
                .carID(carId)
                .type(carDTO.getType())
                .year(carDTO.getYear())
                .fuelType(carDTO.getFuelType())
                .power(carDTO.getPower())
                .gearbox(carDTO.getGearbox())
                .color(carDTO.getColor())
                .photo(carDTO.getPhoto())
                .description(carDTO.getDescription())
                .doorsNumber(carDTO.getDoorsNumber())
                .build();
    }
}
