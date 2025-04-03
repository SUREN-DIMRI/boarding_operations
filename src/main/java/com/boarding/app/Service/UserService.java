package com.boarding.app.Service;

import com.boarding.app.DTO.UserDTO;
import com.boarding.app.DTO.UserRequestDTO;
import com.boarding.app.Entity.User;
import com.boarding.app.Mapper.UserMapper;
import com.boarding.app.Repository.UserRepository;

import jakarta.annotation.PostConstruct;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder; // Encrypt passwords

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserRequestDTO userRequestDTO) {
        // Convert DTO to Entity
        User user = userMapper.toEntity(userRequestDTO);

        // Save user to DB
        user = userRepository.save(user);

        // Convert Entity to DTO and return
        return userMapper.toDTO(user);
    }

    // method to find user by username (for authentication)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Determine role based on roleId
    public String getUserRole(User user) {
        return user.getRoleId() == 1 ? "ROLE_ADMIN" : "ROLE_USER";
    }

    public void deleteUser(long userId) {
        if (userId == 1L) { // Assuming Super Admin has userId = 1
            throw new IllegalStateException("Super Admin cannot be deleted!");
        }
        userRepository.deleteById(userId);
    }

    public UserDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User existingUser = existingUserOptional.get();
        // Check if the user is Super Admin
        if (existingUser.getUserId() == 1L) {
            throw new IllegalStateException("Super Admin cannot be updated!");
        }
        // Update only if values are provided
        if (userRequestDTO.getUsername() != null) {
            existingUser.setUsername(userRequestDTO.getUsername());
        }
        if (userRequestDTO.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(userRequestDTO.getPassword())); // Encrypt password
        }

        User updatedUser = userRepository.save(existingUser);

        return userMapper.toDTO(updatedUser);
    }


    @PostConstruct
    public void initAdminUser() {
        if (!userRepository.findByUsername("Super Admin").isPresent()) {
            User admin = new User();
            admin.setRoleId(1); // RoleID = 1 (Super Admin)
            admin.setUsername("Super Admin");
            admin.setPassword(passwordEncoder.encode("boardops.admin")); // Secure password
            userRepository.save(admin);
        }
    }

    public User saveUser(User user) {
        if ("Super Admin".equals(user.getUsername())) {
            throw new IllegalStateException("Super Admin already exists!");
        }

        user.setRoleId(2); // Assign roleId = 2 (Regular User)
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleId() == 1 ? "ADMIN" : "USER"))
        );
    }

    public String loginUser(UserRequestDTO userRequestDTO) {
        Optional<User> user = userRepository.findByUsername(userRequestDTO.getUsername());

        if (user.isPresent() && passwordEncoder.matches(userRequestDTO.getPassword(), user.get().getPassword())) {
            return "Login successful"; // Later, we'll replace this with a JWT token
        } else {
            return "Invalid credentials";
        }
    }
    
}
