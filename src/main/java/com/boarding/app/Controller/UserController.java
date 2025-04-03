package com.boarding.app.Controller;

import com.boarding.app.DTO.UserDTO;
import com.boarding.app.DTO.UserRequestDTO;
import com.boarding.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }
    
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserRequestDTO userrequestDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userrequestDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
