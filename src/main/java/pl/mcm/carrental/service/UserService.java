package pl.mcm.carrental.service;

import pl.mcm.carrental.model.User;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {

    List<User> getAllUsers(int page, int size);

    User getUserById(Long id);

    User getUserByEmail(String email);

    User addUser(User user);

    User editUser(String email, User user);

    User addRoleToUser(Long userId ,Long roleId);
    
}
