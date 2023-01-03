package com.backend.googleservice.service;

import com.backend.googleservice.dto.UserGoogleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoogleService {

    private final WebClient.Builder webClientBuilder;
    public void userAuth(Map<String,Object> attributes){
        UserGoogleDTO userGoogleDTO = UserGoogleDTO.builder()
                .userName(String.valueOf(attributes.get("name")).replaceAll("\\s+",""))
                .email(String.valueOf(attributes.get("email")))
                .provider("Google")
                .build();

        webClientBuilder.build().post()
                .uri("http://user-service/api/user/registerWithGoogle",
                        uriBuilder -> uriBuilder.build())
                .body(Mono.just(userGoogleDTO), UserGoogleDTO.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }
}
