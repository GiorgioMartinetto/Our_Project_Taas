package com.backend.profileservice.controller;

import com.backend.profileservice.dto.ProfileRequest;
import com.backend.profileservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfile(@RequestBody ProfileRequest profileRequest){
        System.out.println("Profile creation ...");
        profileService.createProfile(profileRequest);
    }

    @PostMapping("/updateName")
    @ResponseStatus(HttpStatus.OK)
    public void updateProfileName(@RequestBody ProfileRequest profileRequest){
        System.out.println("Profile name updating ...");
        profileService.updateProfileName(profileRequest);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProfile(@RequestBody ProfileRequest profileRequest){
        System.out.println("Profile deletion ...");
        profileService.deleteProfile(profileRequest);
    }


}
