package com.backend.googleservice.controller;

import com.backend.googleservice.service.GoogleService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping("/api/googleAuth")
@RequiredArgsConstructor
public class GoogleController {

    private final GoogleService googleService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public void userAuth(Principal principal){
        System.out.println("Registration OK ...");
        googleService.userAuth(principal);
    }

}
