package com.boarding.app.Config;

import com.boarding.app.Entity.User;
import com.boarding.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
// This class is responsible for initializing the database with default data when the application starts.
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Encrypt passwords

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("Super Admin").isEmpty()) {
            User admin = new User();
            admin.setRoleId(1); // Admin Role
            admin.setUsername("Super Admin");
            admin.setPassword(passwordEncoder.encode("boardops.admin")); // Encrypt password before saving
            admin.setUserId(1L); // Set userId to 1 for Super Admin

            userRepository.save(admin);
            System.out.println("Super Admin user created!");
        }
    }
}
