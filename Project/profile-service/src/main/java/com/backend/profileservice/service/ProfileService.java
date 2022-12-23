package com.backend.profileservice.service;

import com.backend.profileservice.dto.ProfileRequest;
import com.backend.profileservice.model.Profile;
import com.backend.profileservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    public void createProfile(ProfileRequest profileRequest){
        Profile profile = Profile.builder()
                .profileName(profileRequest.getProfileName())
                .ownerName(profileRequest.getOwnerName())
                .build();

        profileRepository.save(profile);

        log.info(profile.toString());
    }
}
