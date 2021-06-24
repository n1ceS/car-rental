package pl.mcm.carrental.service.implementation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mcm.carrental.exception.AccessDeniedException;
import pl.mcm.carrental.exception.ResourceNotFoundException;
import pl.mcm.carrental.model.Rent;
import pl.mcm.carrental.model.RentStatus;
import pl.mcm.carrental.model.User;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.repository.CarRepository;
import pl.mcm.carrental.repository.RentRepository;
import pl.mcm.carrental.repository.RentStatusRepository;
import pl.mcm.carrental.repository.UserRepository;
import pl.mcm.carrental.service.RentService;
import pl.mcm.carrental.utils.ConstantAppValues;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    private final CarRepository carRepository;

    private final RentStatusRepository rentStatusRepository;

    private final UserRepository userRepository;

    public RentServiceImpl(RentRepository rentRepository, CarRepository carRepository, RentStatusRepository rentStatusRepository, UserRepository userRepository) {
        this.rentRepository = rentRepository;
        this.carRepository = carRepository;
        this.rentStatusRepository = rentStatusRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Rent addRent(Rent rent, String username) {
        Long carId = rent.getCarID();
        Long userId = userRepository.findUserByEmail(username).get().getId();
        rent.setUserID(userId);
        BigDecimal carPrice = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId)).getPrice();
        long daysBetween = DAYS.between(rent.getStartDate(), rent.getEndDate());
        rent.setTotalCost(carPrice.multiply(new BigDecimal(daysBetween)));
        return rentRepository.save(rent);
    }

    @Override
    @Transactional
    public Rent editRent(Long id, Rent rent, String username) {
        BigDecimal carPrice = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "id", id)).getPrice();
        Rent rentToEdit = rentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("rent", "id", id));
        rentToEdit.setCarID(rent.getCarID());
        rentToEdit.setStartDate(rent.getStartDate());
        rentToEdit.setEndDate(rent.getEndDate());
        long daysBetween = DAYS.between(rent.getStartDate(), rent.getEndDate());
        rent.setTotalCost(carPrice.multiply(new BigDecimal(daysBetween)));
        return rentToEdit;
    }

    @Override
    @Transactional
    public Rent cancelRent(Long rentId, String username) {
        Long userId = userRepository.findUserByEmail(username).get().getId();
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("rent", "id", rentId));
        if(rent.getUserID() != userId) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete profile of: " + username);
            throw new AccessDeniedException(apiResponse);
        }
        RentStatus rentStatus = rentStatusRepository.getById("CANCELED");
        rent.setRentStatus(rentStatus);
        return rent;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Rent changeStatus(Long rentId, String status) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("rent", "id", rentId));
        RentStatus rentStatus = rentStatusRepository.findById(status).orElseThrow(() -> new ResourceNotFoundException("status", "id", rentId));
        rent.setRentStatus(rentStatus);
        return rent;
    }

    @Override
    public List<Rent> getAllRents(int page, int size, String username) {
        User user  = userRepository.findUserByEmail(username).get();
        return rentRepository.findAllByUserID(user.getId(), PageRequest.of(page, size, Sort.by(ConstantAppValues.DEFAULT_SORT_DIRECTION, "id")));
    }

    @Override
    public Rent getRentById(Long rentId, String username) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("rent", "id", rentId));
        Long userId = userRepository.findUserByEmail(username).get().getId();
        if(rent.getUserID() != userId) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete profile of: " + username);
            throw new AccessDeniedException(apiResponse);
        }
        return rent;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ApiResponse deleteRent(Long rentId) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("rent", "id", rentId));
        rentRepository.delete(rent);
        return new ApiResponse(Boolean.TRUE, "Rent with id: "+ rentId +" has been deleted!");
    }

    @Override
    public List<Rent> getRentsByStatus(String username, String status) {
        User user  = userRepository.findUserByEmail(username).get();
        return rentRepository.findAllByRentStatusAndUserID(status, user.getId());
    }

    @Override
    public List<Rent> getRentsByUserId(Long userId, int page, int size) {
        return rentRepository.findAllByUserID(userId, PageRequest.of(page, size, Sort.by(ConstantAppValues.DEFAULT_SORT_DIRECTION, "id")));
    }
}
