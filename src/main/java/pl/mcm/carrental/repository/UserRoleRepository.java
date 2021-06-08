package pl.mcm.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mcm.carrental.model.User;

public interface UserRoleRepository extends JpaRepository<User, Long> {
}
