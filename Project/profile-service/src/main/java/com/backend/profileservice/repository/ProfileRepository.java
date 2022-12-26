package com.backend.profileservice.repository;

import com.backend.profileservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Optional<Profile> getProfileByOwnerName(String owner);
    void deleteProfileByProfileNameAndOwnerName(String profileName, String ownerName);
}
