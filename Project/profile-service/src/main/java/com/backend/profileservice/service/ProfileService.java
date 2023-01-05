package com.backend.profileservice.service;

import com.backend.profileservice.dto.ProfileRequest;
import com.backend.profileservice.model.Profile;
import com.backend.profileservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    public void createProfile(ProfileRequest profileRequest){
        if (profileRepository.countByOwnerEmail(profileRequest.getOwnerEmail()) < 4) {
            Profile _profile = Profile.builder()
                .profileName(profileRequest.getProfileName())
                .ownerEmail(profileRequest.getOwnerEmail())
                .build();

            profileRepository.save(_profile);

            log.info(_profile.toString());
        } else 
            log.info("you cannot create more than 4 profiles");
    }

    public void updateProfileName(ProfileRequest profileRequest){
        Optional<Profile> profile = profileRepository.getProfileByOwnerEmail(profileRequest.getOwnerEmail());

        if(profile.isPresent()){
            Profile _profile = profile.get();
            _profile.setProfileName(profileRequest.getProfileName());
            profileRepository.save(_profile);
        } else {
            throw new IllegalArgumentException("Profile is not present ...");
        }
    }

        //TODO:DEVE ESSERCI SEMPRE UN PROFILO ESISTENTE.
    public void deleteProfile(ProfileRequest profileRequest){
        try {
            if (profileRepository.countByOwnerEmail(profileRequest.getOwnerEmail()) > 1) {
                profileRepository.deleteProfileByProfileNameAndOwnerEmail(
                    profileRequest.getProfileName(), profileRequest.getOwnerEmail());
                log.info("deleted profile with name " + profileRequest.getProfileName() + 
                    " and ownerEmail " + profileRequest.getOwnerEmail());
            } else 
                log.info("you must have at least 1 active profile");

        } catch (Exception e) {
            System.out.println("Profile deletion went wrong!");
        }
    }

    public void unsubscriptionUser(String email){
        profileRepository.deleteAllByOwnerEmail(email);
    }

}
