package com.backend.googleservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class GoogleService {

    private final WebClient.Builder webClientBuilder;
    public void userAuth(Principal principal){
        System.out.println(principal.getName());

    }
}
