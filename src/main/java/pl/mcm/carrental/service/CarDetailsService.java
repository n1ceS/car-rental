package pl.mcm.carrental.service;

import pl.mcm.carrental.dto.CarDTO;
import pl.mcm.carrental.model.CarDetails;
import pl.mcm.carrental.model.CarStatus;
import pl.mcm.carrental.payload.ApiResponse;

import java.util.List;

public interface CarDetailsService {



    CarDetails addCarDetails(CarDetails carDetails);

    CarDetails editCarDetails(Long id, CarDetails carDetails);

    ApiResponse deleteCarDetails(Long carId);

    CarDetails getDetailsByCarId(Long carId);

}
