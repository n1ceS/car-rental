package pl.mcm.carrental.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.mcm.carrental.dto.UserDTO;
import pl.mcm.carrental.model.User;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.service.UserService;
import pl.mcm.carrental.utils.ConstantAppValues;
import pl.mcm.carrental.utils.UserConverter;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam(value = "page", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = ConstantAppValues.DEFAULT_PAGE_SIZE) Integer size) {
        List<User> users = userService.getAllUsers(page, size);

        return new ResponseEntity<>(users.stream().map(UserConverter::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = UserConverter.convertToDto(userService.getUserByEmail(email));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> editUser(@Valid @RequestBody UserDTO userDTO, @ApiParam(hidden = true) @AuthenticationPrincipal String username) {
        User user = UserConverter.convertToEntity(userDTO);
        user = userService.editUser(userDTO.getEmail(), user, username);
        return new ResponseEntity<>(UserConverter.convertToDto(user), HttpStatus.OK);
    }

}
