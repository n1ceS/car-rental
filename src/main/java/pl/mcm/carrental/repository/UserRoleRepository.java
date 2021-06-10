package pl.mcm.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mcm.carrental.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
