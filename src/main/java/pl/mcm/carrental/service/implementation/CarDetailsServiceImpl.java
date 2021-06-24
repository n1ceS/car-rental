package pl.mcm.carrental.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mcm.carrental.dto.CarDTO;
import pl.mcm.carrental.exception.ResourceNotFoundException;
import pl.mcm.carrental.model.CarDetails;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.repository.CarDetailsRepository;
import pl.mcm.carrental.service.CarDetailsService;

@RequiredArgsConstructor
@Service
public class CarDetailsServiceImpl implements CarDetailsService {

    private CarDetailsRepository carDetailsRepository;

    @Autowired
    public CarDetailsServiceImpl(CarDetailsRepository carDetailsRepository) {
        this.carDetailsRepository = carDetailsRepository;
    }

    @Override
    public CarDetails addCarDetails(CarDetails carDetails) {

        return carDetailsRepository.save(carDetails);
    }

    @Override
    @Transactional
    public CarDetails editCarDetails(Long id, CarDetails carDetails) {
        CarDetails carDetailsToEdit = carDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("carDetails", "id", carDetails.getCarID()));
        carDetailsToEdit.setType(carDetails.getType());
        carDetailsToEdit.setYear(carDetails.getYear());
        carDetailsToEdit.setFuelType(carDetails.getFuelType());
        carDetailsToEdit.setPower(carDetails.getPower());
        carDetailsToEdit.setGearbox(carDetails.getGearbox());
        carDetailsToEdit.setColor(carDetails.getColor());
        carDetailsToEdit.setPhoto(carDetails.getPhoto());
        carDetailsToEdit.setDescription(carDetails.getDescription());
        carDetailsToEdit.setDoorsNumber(carDetails.getDoorsNumber());
        return carDetailsToEdit;
    }

    @Override
    @Transactional
    public ApiResponse deleteCarDetails(Long carId) {
        carDetailsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("car", "id", carId));
        carDetailsRepository.deleteById(carId);
        return new ApiResponse(Boolean.TRUE, "You successfully deleted car");
    }

    @Override
    @Transactional
    public CarDetails getDetailsByCarId(Long carId) {
        return carDetailsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("car", "id", carId));
    }

}
