package pl.mcm.carrental.controller;

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

        return new ResponseEntity<>(users.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = convertToDto(userService.getUserByEmail(email));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        userDTO = convertToDto(userService.addUser(user));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> editUser(@Valid @RequestBody UserDTO userDTO, @AuthenticationPrincipal String username) {
        User user = convertToEntity(userDTO);
        user = userService.editUser(userDTO.getEmail(), user, username);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setPhone(user.getPhone());
        userDTO.setPesel(user.getPesel());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setBirthDate(userDTO.getBirthDate());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPesel(userDTO.getPesel());
        return user;
    }
}
