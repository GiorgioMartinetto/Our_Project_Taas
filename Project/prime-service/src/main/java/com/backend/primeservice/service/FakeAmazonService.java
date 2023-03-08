package com.backend.primeservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.backend.primeservice.dto.AuthUserRequest;

import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FakeAmazonService {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    private static class User{
        private String email;
        private String password;
    }
    private final ResourceLoader resourceLoader;
    private static final String FORMAT="classpath:videos/%s.mp4";
    private List<User> users = new ArrayList<>(Arrays.asList(
        User.builder().email("giorgio.martinetto@outlook.it").password("prova1").build(),
        User.builder().email("giorgio.perna@outlook.it").password("prova2").build(),
        User.builder().email("odino.zeus@gmail.it").password("prova3").build(),
        User.builder().email("badwolf.who@yahoo.it").password("prova4").build()
    ));
    
    public boolean validateUser(AuthUserRequest userRequest){

        User _user = User.builder().email(userRequest.getEmail()).password(userRequest.getPassword()).build();
        
        for (User user: users) {
            if(user.getEmail().equals(_user.getEmail()) && user.getPassword().equals(_user.getPassword())) {
                System.out.println("Authentication OK ...");
                return true;
            }
        }
        return false;
    }

    public Mono<Resource> getFilm(String title){
        System.out.println("=========== GET FILM ===============");
        return Mono.fromSupplier(()->resourceLoader.
                getResource(String.format(FORMAT,title)));
    }
}



