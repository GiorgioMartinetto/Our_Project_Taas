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
        System.out.println(profileRequest.getProfileName()+
                " - "+profileRequest.getOwnerName());
        profileService.createProfile(profileRequest);
    }



}
