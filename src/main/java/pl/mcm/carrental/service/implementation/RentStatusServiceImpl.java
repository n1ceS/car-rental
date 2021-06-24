package pl.mcm.carrental.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mcm.carrental.model.RentStatus;
import pl.mcm.carrental.repository.RentStatusRepository;
import pl.mcm.carrental.service.RentStatusService;

@Service
public class RentStatusServiceImpl implements RentStatusService {
    private final RentStatusRepository rentStatusRepository;

    public RentStatusServiceImpl(RentStatusRepository rentStatusRepository) {
        this.rentStatusRepository = rentStatusRepository;
    }

    @Override
    @Transactional
    public RentStatus addRentStatus(RentStatus rentStatus) {
        return rentStatusRepository.save(rentStatus);
    }

    @Override
    public RentStatus getRentStatusByName(String name) {
        return rentStatusRepository.getById(name);
    }
}
