package pl.mcm.carrental.service.implementation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mcm.carrental.exception.AccessDeniedException;
import pl.mcm.carrental.exception.BadRequestException;
import pl.mcm.carrental.exception.ResourceNotFoundException;
import pl.mcm.carrental.model.User;
import pl.mcm.carrental.model.UserRole;
import pl.mcm.carrental.payload.ApiResponse;
import pl.mcm.carrental.repository.UserRepository;
import pl.mcm.carrental.repository.UserRoleRepository;
import pl.mcm.carrental.service.UserService;
import pl.mcm.carrental.utils.ConstantAppValues;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers(int page, int size) {
        return userRepository.findAllUsers(PageRequest.of(page, size, Sort.by(ConstantAppValues.DEFAULT_SORT_DIRECTION, "id")));
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "user", id));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user", "email", email));
        return user;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            ApiResponse apiResponse =  new ApiResponse(Boolean.FALSE, "Email is already taken!");
            throw new BadRequestException(apiResponse);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User editUser(String email, User user, String username) {
        User userToEdit = userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user", "email", email));
        if(email != username) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "No permission to edit user with username" + username);
            throw new AccessDeniedException(apiResponse);
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken!");
            throw new BadRequestException(apiResponse);
        }
        userToEdit.setFirstname(user.getFirstname());
        userToEdit.setLastname(user.getLastname());
        userToEdit.setPassword(user.getPassword());
        userToEdit.setPhone(user.getPhone());
        userToEdit.setBirthDate(user.getBirthDate());
        return userToEdit;
    }

    @Override
    @Transactional
    public User addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        UserRole userRole = userRoleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("role", "id", roleId));
        user.getUserRoleSet().add(userRole);
        return user;
    }

    @Override
    @Transactional
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
