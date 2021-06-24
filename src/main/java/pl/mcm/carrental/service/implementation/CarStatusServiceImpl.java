package pl.mcm.carrental.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mcm.carrental.exception.ResourceNotFoundException;
import pl.mcm.carrental.model.Car;
import pl.mcm.carrental.model.CarStatus;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.repository.CarStatusRepository;
import pl.mcm.carrental.service.CarStatusService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarStatusServiceImpl implements CarStatusService {

    private final CarStatusRepository carStatusRepository;

    public CarStatusServiceImpl(CarStatusRepository carStatusRepository) {
        this.carStatusRepository = carStatusRepository;
    }

    @Override
    @Transactional
    public CarStatus addCarStatus(CarStatus carStatus) {
        return carStatusRepository.save(carStatus);
    }

    @Override
    @Transactional
    public CarStatus editCarStatus(CarStatus carStatus) {
        CarStatus carStatusToEdit = carStatusRepository.findById(carStatus.getCarStatus()).orElseThrow(() -> new ResourceNotFoundException("carStatus", "id", carStatus.getCarStatus()));
        carStatusToEdit.setDescription(carStatus.getDescription());
        return carStatusToEdit;
    }

    @Override
    @Transactional
    public ApiResponse deleteCarStatus(String carStatusId) {
        CarStatus carStatus = carStatusRepository.findById(carStatusId).orElseThrow(() -> new ResourceNotFoundException("car", "id", carStatusId));
        carStatusRepository.delete(carStatus);
        return new ApiResponse(Boolean.TRUE, "You successfully deleted car status");
    }

    @Override
    public List<CarStatus> getAllCarStatuses() {
        return carStatusRepository.findAll();
    }

    @Override
    public CarStatus getCarStatusById(String status) {
        return carStatusRepository.findById(status).orElseThrow(() -> new ResourceNotFoundException("status", "id", status));
    }
}
