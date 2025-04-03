package com.boarding.app.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private Long userId;
    private Integer roleId;
    private String username;
}
