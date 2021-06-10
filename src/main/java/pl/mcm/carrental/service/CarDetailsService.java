package pl.mcm.carrental.service;

import pl.mcm.carrental.model.CarDetails;
import pl.mcm.carrental.model.CarStatus;
import pl.mcm.carrental.payload.ApiResponse;

import java.util.List;

public interface CarDetailsService {



    CarDetails addCarDetails(CarDetails carDetails);

    CarDetails editCarStatus(CarDetails carDetails);

    ApiResponse deleteCarDetails(long carId);

    CarDetails getDetailsByCarId(long carId);

}
