package com.backend.googleservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.Principal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoogleService {

    private final WebClient.Builder webClientBuilder;
    public void userAuth(Map<String,Object> attributes){
        String name = String.valueOf(attributes.get("email"));
        System.out.println(name);

    }
}
