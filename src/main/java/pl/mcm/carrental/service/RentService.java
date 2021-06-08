package pl.mcm.carrental.service;

import pl.mcm.carrental.model.Rent;

import java.awt.print.Pageable;
import java.util.List;

public interface RentService {

    Rent addRent(Rent Rent);

    Rent editRent(Rent Rent);

    Rent cancelRent(long rentId);

    Rent changeStatus(long rentId, long statusId);

    List<Rent> getAllRents(Pageable page);

    Rent getRentById(long rentId);

    void deleteRent(long rentId);

    List<Rent> getRentsByRentedStatus(Pageable pagea);

    List<Rent> getRentsByReservedStatus(Pageable page);

    List<Rent> getRentsByCanceledStatus(Pageable page);

    List<Rent> getRentsByReturnedStatus(Pageable page);

    List<Rent> getRentsByUserId(long userId);

}
