package com.backend.userservice.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionAuthDto {
    private String emailPlatform;
    private String password;
    private String userName;
    private String platform;
}
