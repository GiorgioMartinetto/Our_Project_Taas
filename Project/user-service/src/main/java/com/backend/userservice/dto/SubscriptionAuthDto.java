package com.backend.userservice.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionAuthDto {
    private String email;
    private String password;

    private String platform;
}
