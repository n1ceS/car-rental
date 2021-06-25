package pl.mcm.carrental.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mcm.carrental.dto.UserDTO;
import pl.mcm.carrental.model.User;
import pl.mcm.carrental.service.UserService;

import javax.validation.Valid;

import static pl.mcm.carrental.utils.UserConverter.convertToDto;
import static pl.mcm.carrental.utils.UserConverter.convertToEntity;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        userDTO = convertToDto(userService.addUser(user));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
