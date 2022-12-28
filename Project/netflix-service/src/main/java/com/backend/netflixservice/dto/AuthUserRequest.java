package com.backend.netflixservice.dto;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthUserRequest {
    private String email;
    private String password;
}
