package com.backend.googleservice.service;

import com.backend.googleservice.dto.UserGoogleDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class GoogleService {

    private final WebClient.Builder webClientBuilder;
    
    public void userLogin(Map<String,Object> attributes){
        UserGoogleDTO userGoogleDTO = UserGoogleDTO.builder()
                .userName(String.valueOf(attributes.get("name")).replaceAll("\\s+",""))
                .email(String.valueOf(attributes.get("email")))
                .provider("Google")
                .build();
        webClientBuilder.build().post()
                .uri("http://user-service/api/user/loginWithGoogle", uriBuilder -> uriBuilder.build())
                .body(Mono.just(userGoogleDTO), UserGoogleDTO.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        
    }

}
