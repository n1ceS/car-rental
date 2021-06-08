package pl.mcm.carrental.service;

import pl.mcm.carrental.model.CarStatus;
import pl.mcm.carrental.payload.ApiResponse;

import java.util.List;

public interface CarStatusService {


    CarStatus addCarStatus(CarStatus carStatus);

    CarStatus editCarStatus(CarStatus carStatus);

    ApiResponse deleteCarStatus(String carStatusId);

    List<CarStatus> getAllCarStatuses();

    CarStatus getCarStatusById(String status);
}
