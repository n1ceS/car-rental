package pl.mcm.carrental.service;

import pl.mcm.carrental.model.User;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {

    List<User> getAllUsers(Pageable page);

    User getUserById(long id);

    User getUserByEmail(String email);

    User addUser(User user);

    User editUser(User user);

    User addRoleToUser(long userId ,long roleId);
    
}
