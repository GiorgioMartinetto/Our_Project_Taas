package com.backend.profileservice.repository;

import com.backend.profileservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Optional<Profile> getProfileByOwnerEmail(String ownerEmail);
    void deleteProfileByProfileNameAndOwnerEmail(String profileName, String ownerEmail);

    void deleteAllByOwnerEmail(String email);

    long countByOwnerEmail(String email);

    List<Profile> getProfilesByOwnerEmail(String email);
}
