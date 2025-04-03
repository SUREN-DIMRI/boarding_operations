package com.boarding.app.Repository;

import com.boarding.app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUserId(Long userId);
    Optional<User> findByRoleId(Long roleId);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsernameAndRoleId(String username, Long roleId);
    Optional<User> findByUserIdAndRoleId(Long userId, Long roleId);
}
// This interface extends JpaRepository, which provides CRUD operations for the User entity.
// It includes methods to find users by username, userId, roleId, and a combination of username and password.
