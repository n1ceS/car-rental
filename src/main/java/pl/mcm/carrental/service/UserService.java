package pl.mcm.carrental.service;

import pl.mcm.carrental.model.User;
import pl.mcm.carrental.payload.ApiResponse;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers(int page, int size);

    User getUserById(Long id);

    User getUserByEmail(String email);

    User addUser(User user);

    User editUser(String email, User user);

    User addRoleToUser(Long userId ,Long roleId);

    Optional<User> findUserByEmail(String email);
}
