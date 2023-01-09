package com.backend.netflixservice.service;

import com.backend.netflixservice.dto.AuthUserRequest;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeNetflixService {

    private final ResourceLoader resourceLoader;
    private static final String FORMAT="classpath:videos/%s.mp4";
    private List<User> users = new ArrayList<>(Arrays.asList(
            User.builder().email("giorgio.martinetto@outlook.it").password("prova1").build(),
            User.builder().email("giorgio.perna@outlook.it").password("prova2").build(),
            User.builder().email("marius@outlook.it").password("prova1").build(),
            User.builder().email("perna12@gmail.com").password("prova2").build(),
            User.builder().email("giorgio@gmail.com").password("prova1").build()
    ));
    
    public boolean validateUser(AuthUserRequest userRequest){
        User _user = User.builder().email(userRequest.getEmail()).password(userRequest.getPassword()).build();
        for (User user: users) {
            if(user.getEmail().equals(_user.getEmail()) && user.getPassword().equals(_user.getPassword())){
                System.out.println("Authentication OK ...");
                return true;
            }
        }
        return false;
    }

    public Mono<Resource> getFilm(String title){
        return Mono.fromSupplier(()->resourceLoader.
                getResource(String.format(FORMAT,title)));
    }
    
    
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
class User{
    private String email;
    private String password;
}