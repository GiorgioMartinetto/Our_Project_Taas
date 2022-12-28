package com.backend.userservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthUserRequest {
    private String email;
    private String password;
}
