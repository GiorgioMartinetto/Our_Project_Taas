package com.backend.googleservice.controller;

import com.backend.googleservice.service.GoogleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/googleAuth")
@RequiredArgsConstructor
public class GoogleController {

    private final GoogleService googleService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public void userAuth(@AuthenticationPrincipal  OAuth2AuthenticatedPrincipal principal){
        System.out.println("Registration OK ...");
        Map<String, Object> attributes = principal.getAttributes();
        googleService.userAuth(attributes);

    }

}
