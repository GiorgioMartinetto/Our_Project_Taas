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
        Profile _profile = Profile.builder()
                .profileName(profileRequest.getProfileName())
                .ownerName(profileRequest.getOwnerName())
                .build();

        profileRepository.save(_profile);

        log.info(_profile.toString());
    }

    public void updateProfileName(ProfileRequest profileRequest){
        Optional<Profile> profile = profileRepository.getProfileByOwnerName(profileRequest.getOwnerName());

        if(profile.isPresent()){
            Profile _profile = profile.get();
            _profile.setProfileName(profileRequest.getProfileName());
            profileRepository.save(_profile);
        }else{
            throw new IllegalArgumentException("Profile is not present ...");
        }
    }

    public void deleteProfile(ProfileRequest profileRequest){
        try{
            profileRepository.deleteProfileByProfileNameAndOwnerName(
                    profileRequest.getProfileName(), profileRequest.getOwnerName());

        }catch (Exception e){
            System.out.println("Profile deletion went wrong!");
        }
    }

    public void unsubscriptionUser(String name){
        profileRepository.deleteAllByOwnerName(name);
    }

}
