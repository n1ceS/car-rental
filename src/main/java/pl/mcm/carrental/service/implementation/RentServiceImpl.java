package pl.mcm.carrental.service.implementation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import pl.mcm.carrental.exception.ResourceNotFoundException;
import pl.mcm.carrental.model.Rent;
import pl.mcm.carrental.model.RentStatus;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.repository.RentRepository;
import pl.mcm.carrental.repository.RentStatusRepository;
import pl.mcm.carrental.service.RentService;
import pl.mcm.carrental.utils.ConstantAppValues;

import java.awt.print.Pageable;
import java.util.List;

public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    private final RentStatusRepository rentStatusRepository;

    public RentServiceImpl(RentRepository rentRepository, RentStatusRepository rentStatusRepository) {
        this.rentRepository = rentRepository;
        this.rentStatusRepository = rentStatusRepository;
    }

    @Override
    public Rent addRent(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public Rent editRent(Long id, Rent rent) {
        Rent rentToEdit = rentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("rent", "id", id));
        rentToEdit.setCarID(rent.getCarID());
        rentToEdit.setUserID(rent.getUserID());
        rentToEdit.setStartDate(rent.getStartDate());
        rentToEdit.setEndDate(rent.getEndDate());
        return rentToEdit;
    }

    @Override
    @Transactional
    public Rent cancelRent(Long rentId) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("rent", "id", rentId));
        RentStatus rentStatus = rentStatusRepository.getById("CANCELED");
        rent.setRentStatus(rentStatus);
        return rent;
    }

    @Override
    public Rent changeStatus(Long rentId, String status) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("rent", "id", rentId));
        RentStatus rentStatus = rentStatusRepository.findById(status).orElseThrow(() -> new ResourceNotFoundException("status", "id", rentId));
        rent.setRentStatus(rentStatus);
        return rent;
    }

    @Override
    public List<Rent> getAllRents(int page, int size) {
        return rentRepository.findAllRents(PageRequest.of(page, size, Sort.by(ConstantAppValues.DEFAULT_SORT_DIRECTION, "id")));
    }

    @Override
    public Rent getRentById(Long rentId) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("rent", "id", rentId));
        return rent;
    }

    @Override
    public ApiResponse deleteRent(Long rentId) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("rent", "id", rentId));
        rentRepository.delete(rent);
        return new ApiResponse(Boolean.TRUE, "Rent with id: "+ rentId +" has been deleted!");
    }

    @Override
    public List<Rent> getRentsByRentedStatus(Pageable pagea) {
        return null;
    }

    @Override
    public List<Rent> getRentsByReservedStatus(Pageable page) {
        return null;
    }

    @Override
    public List<Rent> getRentsByCanceledStatus(Pageable page) {
        return null;
    }

    @Override
    public List<Rent> getRentsByReturnedStatus(Pageable page) {
        return null;
    }

    @Override
    public List<Rent> getRentsByUserId(Long userId) {
        return null;
    }
}
