package com.backend.userservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String userName;
    private String email;
    private String password;
    private String provider;
}
