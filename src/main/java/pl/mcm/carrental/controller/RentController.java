package pl.mcm.carrental.controller;

import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.mcm.carrental.dto.RentDTO;
import pl.mcm.carrental.model.Rent;
import pl.mcm.carrental.model.RentStatus;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.service.CarService;
import pl.mcm.carrental.service.RentService;
import pl.mcm.carrental.service.RentStatusService;
import pl.mcm.carrental.service.UserService;
import pl.mcm.carrental.utils.ConstantAppValues;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rents")
@AllArgsConstructor
public class RentController {
    private final RentService rentService;

    private final CarService carService;

    private final UserService userService;

    private final RentStatusService rentStatusService;

    @GetMapping
    public ResponseEntity<List<RentDTO>> getAllRents( @RequestParam(value = "page", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE) Integer page,
                                                      @RequestParam(value = "size", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE_SIZE) Integer size,
                                                      @ApiParam(hidden = true) @AuthenticationPrincipal String username) {
        List<Rent> rents = rentService.getAllRents(page, size, username);


        return new ResponseEntity<>(rents.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> deleteRent(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(rentService.deleteRent(id), HttpStatus.OK);
    }

    @PutMapping("/{rent_id}")
    public ResponseEntity<RentDTO> editRent(@PathVariable(name = "rent_id") Long rent_id, @RequestBody @Valid RentDTO rentDTO, @ApiParam(hidden = true) @AuthenticationPrincipal String username) {
        Rent rent = convertToEntity(rentDTO);
        rent = rentService.editRent(rent_id, rent, username);
        return new ResponseEntity<>(convertToDto(rent), HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<RentDTO> cancelRent(@PathVariable(name = "id") long id, @ApiParam(hidden = true) @AuthenticationPrincipal String username) {
        return new ResponseEntity<>(convertToDto(rentService.cancelRent(id, username)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RentDTO> addRent(@RequestBody @Valid RentDTO rentDTO, @ApiParam(hidden = true) @AuthenticationPrincipal String username) {
        Rent rent = convertToEntity(rentDTO);
        RentStatus rentStatus = rentStatusService.getRentStatusByName("NEW");
        rent.setRentStatus(rentStatus);
        rent = rentService.addRent(rent, username);

        return new ResponseEntity<>(convertToDto(rent), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentDTO> getRentById(@PathVariable(name = "id") Long id, @AuthenticationPrincipal String username) {
        Rent rent =  rentService.getRentById(id, username);
        return new ResponseEntity<>(convertToDto(rent), HttpStatus.OK);
    }


    @GetMapping("/status/new")
    public ResponseEntity<List<RentDTO>> getNewRents(@AuthenticationPrincipal String username) {
        List<Rent> rents =  rentService.getRentsByStatus(username, "NEW");
        return new ResponseEntity<>(rents.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/status/rented")
    public ResponseEntity<List<RentDTO>> getRentedRents(@AuthenticationPrincipal String username) {
        List<Rent> rents =  rentService.getRentsByStatus(username, "RENTED");
        return new ResponseEntity<>(rents.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/status/reserved")
    public ResponseEntity<List<RentDTO>> getReservedRents(@AuthenticationPrincipal String username) {
        List<Rent> rents =  rentService.getRentsByStatus(username, "RESERVED");
        return new ResponseEntity<>(rents.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/status/canceled")
    public ResponseEntity<List<RentDTO>> getCanceledRents(@AuthenticationPrincipal String username) {
        List<Rent> rents =  rentService.getRentsByStatus(username, "CANCELED");
        return new ResponseEntity<>(rents.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/status/returned")
    public ResponseEntity<List<RentDTO>> getReturnedRents(@AuthenticationPrincipal String username) {
        List<Rent> rents =  rentService.getRentsByStatus(username, "RETURNED");
        return new ResponseEntity<>(rents.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<RentDTO>> getRentsByUserId(@RequestParam(value = "page", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE) Integer page,
                                                          @RequestParam(value = "size", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE_SIZE) Integer size, @PathVariable(name = "id") Long id) {
        List<Rent> rents =  rentService.getRentsByUserId(id, page, size);
        return new ResponseEntity<>(rents.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/{id}/status/{status_name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RentDTO> changeStatus(@PathVariable(name = "id") Long id, @PathVariable(value = "status_name") String status_name) {
        Rent rent =  rentService.changeStatus(id, status_name);
        return new ResponseEntity<>(convertToDto(rent), HttpStatus.OK);
    }
    private RentDTO convertToDto(Rent rent) {
        RentDTO rentDTO = new RentDTO();

        rentDTO.setId(rent.getId());
        rentDTO.setCarID(rent.getCarID());
        rentDTO.setUserID(rent.getUserID());
        rentDTO.setTotalCost(rent.getTotalCost());
        rentDTO.setStartDate(rent.getStartDate());
        rentDTO.setEndDate(rent.getEndDate());
        rentDTO.setRentStatus(rent.getRentStatus().getRentStatus());
        return rentDTO;
    }

   private Rent convertToEntity(RentDTO rentDTO) {
        Rent rent = new Rent();
        rent.setId(rentDTO.getId());
        rent.setCarID(rentDTO.getCarID());
        rent.setStartDate(rentDTO.getStartDate());
        rent.setEndDate(rentDTO.getEndDate());
        return rent;
    }
}
