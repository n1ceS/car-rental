package pl.mcm.carrental.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.mcm.carrental.dto.CarDTO;
import pl.mcm.carrental.model.Car;
import pl.mcm.carrental.model.CarDetails;
import pl.mcm.carrental.model.CarStatus;
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

    @Autowired
    private CarService carService;

    @Autowired
    private CarDetailsService carDetailsService;

    @Autowired
    private CarStatusService carStatusService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars(
            @RequestParam(value = "page", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE_SIZE) Integer size) {
        {
            List<Car> cars = carService.getAllCars(page, size);
            return new ResponseEntity<>(cars.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) throws ParseException {
        Car car = convertToEntity(carDTO);
        car.setCarStatus("READY");
        carService.addCar(car);
        carDetailsService.addCarDetails(getCarDetailsFromCarDTO(car.getId(), carDTO));

        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }

    private CarDTO convertToDto(Car car) {
        CarDTO carDTO = new CarDTO();
        CarDetails carDetails = carDetailsService.getDetailsByCarId(car.getId());
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
