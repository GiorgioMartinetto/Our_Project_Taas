package com.backend.userservice.repository;


import com.backend.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getUserByUserName(String userName);

}
