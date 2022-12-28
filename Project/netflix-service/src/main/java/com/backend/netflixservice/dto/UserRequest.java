package com.backend.netflixservice.dto;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String email;
    private String password;
}
