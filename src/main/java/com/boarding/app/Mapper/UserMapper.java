package com.boarding.app.Mapper;

import com.boarding.app.DTO.UserDTO;
import com.boarding.app.DTO.UserRequestDTO;
import com.boarding.app.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Convert DTO to Entity
    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        // Assign role automatically
        if (dto.getUsername().equalsIgnoreCase("Super Admin")) {
            user.setRoleId(1); // Admin role
        } else {
            user.setRoleId(2); // Regular user role
        }

        return user;
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getUserId(), user.getRoleId(), user.getUsername());
    }
}
