package pl.mcm.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.mcm.carrental.dto.CarDTO;
import pl.mcm.carrental.model.Car;
import pl.mcm.carrental.model.CarStatus;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.service.CarStatusService;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carstatuses")
public class CarStatusController {
    @Autowired
    private CarStatusService carStatusService;

    @GetMapping
    public ResponseEntity<List<CarStatus>> getAllCarStatuses() {
            List<CarStatus> cars = carStatusService.getAllCarStatuses();
            return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarStatus> getCarStatusById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(carStatusService.getCarStatusById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarStatus> addCarStatus(@Valid @RequestBody CarStatus carStatus){
        return new ResponseEntity<>(carStatusService.addCarStatus(carStatus), HttpStatus.CREATED);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarStatus> editCarStatus(@Valid @RequestBody CarStatus carStatus) {
        return new ResponseEntity<>(carStatusService.editCarStatus(carStatus), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> deleteCarStatus(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(carStatusService.deleteCarStatus(id), HttpStatus.OK);
    }

}
