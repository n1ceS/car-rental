package pl.mcm.carrental.service;

import pl.mcm.carrental.model.Rent;
import pl.mcm.carrental.model.User;
import pl.mcm.carrental.payload.ApiResponse;

import java.awt.print.Pageable;
import java.util.List;

public interface RentService {

    Rent addRent(Rent Rent, String username);

    Rent editRent(Long id, Rent Rent, String username);

    Rent cancelRent(Long rentId);

    Rent changeStatus(Long rentId, String status);

    List<Rent> getAllRents(int page, int size, String username);

    Rent getRentById(Long rentId);

    ApiResponse deleteRent(Long rentId);

    List<Rent> getRentsByStatus(String username, String status);;

    List<Rent> getRentsByUserId(Long userId, int page, int size);

}
