package pl.mcm.carrental.service;

import pl.mcm.carrental.model.Rent;
import pl.mcm.carrental.payload.ApiResponse;

import java.awt.print.Pageable;
import java.util.List;

public interface RentService {

    Rent addRent(Rent Rent);

    Rent editRent(Long id, Rent Rent);

    Rent cancelRent(Long rentId);

    Rent changeStatus(Long rentId, String status);

    List<Rent> getAllRents(int page, int size);

    Rent getRentById(Long rentId);

    ApiResponse deleteRent(Long rentId);

    List<Rent> getRentsByRentedStatus(Pageable pagea);

    List<Rent> getRentsByReservedStatus(Pageable page);

    List<Rent> getRentsByCanceledStatus(Pageable page);

    List<Rent> getRentsByReturnedStatus(Pageable page);

    List<Rent> getRentsByUserId(Long userId);

}
