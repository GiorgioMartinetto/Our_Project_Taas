package com.backend.profileservice.repository;

import com.backend.profileservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

}
