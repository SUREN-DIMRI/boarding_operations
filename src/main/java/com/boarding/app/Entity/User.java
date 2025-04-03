package com.boarding.app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    @Column(unique = true, nullable = false)
    private Long userId;

    private Integer roleId;  // 1 for Admin, 2 for normal users

    @Column(nullable = false)  // Removed unique constraint
    private String username;

    @Column(nullable = false)
    private String password;


    // @Column(nullable = false)
    // private String email;

    // @Column(nullable = false)
    // private String phoneNumber;

    enum Role {
        ADMIN, USER
    }

}
