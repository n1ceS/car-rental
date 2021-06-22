package pl.mcm.carrental.service;

import pl.mcm.carrental.model.RentStatus;

public interface RentStatusService {

    RentStatus addRentStatus(RentStatus rentStatus);

    RentStatus getRentStatusByName(String name);
}
