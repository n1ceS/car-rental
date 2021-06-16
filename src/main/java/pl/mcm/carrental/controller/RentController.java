package pl.mcm.carrental.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mcm.carrental.dto.RentDTO;
import pl.mcm.carrental.model.Car;
import pl.mcm.carrental.model.Rent;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.service.CarService;
import pl.mcm.carrental.service.RentService;
import pl.mcm.carrental.service.UserService;
import pl.mcm.carrental.utils.ConstantAppValues;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rent")
@AllArgsConstructor
public class RentController {
    private final RentService rentService;

    private final CarService carService;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<RentDTO>> getAllRents( @RequestParam(value = "page", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE) Integer page,
                                                     @RequestParam(value = "size", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE_SIZE) Integer size) {
        List<Rent> rents = rentService.getAllRents(page, size);

        return new ResponseEntity<>(rents.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRent(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(rentService.deleteRent(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentDTO> editRent(@PathVariable(name = "id") Long id, @RequestBody RentDTO rentDTO) throws ParseException {
        Rent rentToEdit =
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<RentDTO> cancelRent(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(convertToDto(rentService.cancelRent(id)), HttpStatus.OK);
    }

    private RentDTO convertToDto(Rent rent) {
        RentDTO rentDTO = new RentDTO();

        rentDTO.setId(rent.getId());
        rentDTO.setBrand(carService.getCarById(rent.getCarID()).getBrand());
        rentDTO.setModel(carService.getCarById(rent.getCarID()).getModel());
        rentDTO.setEmail(userService.getUserById(rent.getUserID()).getEmail());
        rentDTO.setRentStatus(rent.getRentStatus().getRentStatus());
        rentDTO.setDescription(rent.getRentStatus().getDescription());

        return rentDTO;
    }

    private Car convertToEntity(RentDTO rentDTO) {
        Rent rent = new Rent();

        rent.setId(rentDTO.getId());
//        rent.setCarID(rentDTO.ge);
    }
}
