package com.backend.netflixservice.service;

import com.backend.netflixservice.dto.AuthUserRequest;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeNetflixService {

    
    private List<User> users = new ArrayList<>(Arrays.asList(User.builder().email("giorgio.martinetto@outlook.it").password("prova1").build(),
    User.builder().email("giorgio.perna@outlook.it").password("prova2").build()));
    
    public boolean validateUser(AuthUserRequest userRequest){

        
        
        User _user = User.builder().email(userRequest.getEmail()).password(userRequest.getPassword()).build();
        
        for (User user: users) {
            if(user.getEmail().equals(_user.getEmail()) && user.getPassword().equals(_user.getPassword()))
            {
                System.out.println("Authentication OK ...");
                return true;
            }
        }
        
        return false;
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