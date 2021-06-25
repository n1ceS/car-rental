package pl.mcm.carrental.utils;

import pl.mcm.carrental.dto.UserDTO;
import pl.mcm.carrental.model.User;

public class UserConverter {
    public static final UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setPhone(user.getPhone());
        userDTO.setPesel(user.getPesel());
        return userDTO;
    }

    public static final User convertToEntity(UserDTO userDTO) {
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
