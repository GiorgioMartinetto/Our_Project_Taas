package com.backend.userservice.model;

import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Set;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    @Nullable
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Subscription> subscriptions;

    @Column(name = "provider")
    private String provider;

}
