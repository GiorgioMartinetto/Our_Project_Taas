package com.backend.userservice.service;

import com.backend.userservice.dto.*;
import com.backend.userservice.model.User;
import com.backend.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final WebClient.Builder webClientBuilder;

    public void createUser(UserRequest userRequest){
        User user = User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
        userRepository.save(user);

        createProfile(user.getUserName(), "MyProfile");


        log.info("User {} is create", user.getId());
    }


    /*
    * recuperare l'id
    * impacchettarlo con il nome del profilo
    * spedirlo al microservizio del profilo
    * */
    private void createProfile(String ownerName, String profileName){
        ProfileRequest profileRequest = ProfileRequest.builder()
                .profileName(profileName)
                .ownerName(ownerName)
                .build();

        System.out.println("Default Profile ...");

        webClientBuilder.build().post()
                .uri("http://profile-service/api/profile/create",
                        uriBuilder -> uriBuilder.build())
                .body(Mono.just(profileRequest), ProfileRequest.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public void updateEmail(EmailUpdateRequest emailUpdateRequest){
        Optional<User> user = userRepository.findById(emailUpdateRequest.getId());

        if(user.isPresent()) {
            User _user = user.get();
            _user.setEmail(emailUpdateRequest.getEmail()); //TODO:Inseire il controllo sulla mail
            userRepository.save(_user);
        }else{
            throw new IllegalArgumentException("User id invalid");
        }

    }

    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest){
        Optional<User> user = userRepository.findById(passwordUpdateRequest.getId());

        if(user.isPresent()) {
            User _user = user.get();
            if(_user.getPassword().equals(passwordUpdateRequest.getOldPassword()) &&
                !_user.getPassword().equals(passwordUpdateRequest.getNewPassword())){
                _user.setPassword(passwordUpdateRequest.getNewPassword());
                userRepository.save(_user);
            } else {
                throw new IllegalArgumentException("Invalid password");
            }

        }else{
            throw new IllegalArgumentException("User id invalid");
        }

    }

    public void unsubscribeUser(UnsubscribeUserRequest unsubscribeUserRequest){
        Optional<User> user = userRepository.getUserByUserName(unsubscribeUserRequest.getUserName());

        if(user.isPresent()){
            User _user = user.get();
            if( _user.getPassword().equals(unsubscribeUserRequest.getPassword()) ){

                webClientBuilder.build().post()
                        .uri("http://profile-service/api/profile/unsubscription",
                                uriBuilder -> uriBuilder.build())
                        .body(Mono.just(_user.getUserName()), String.class)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();

                userRepository.delete(_user);
            } else {
                throw new IllegalArgumentException("Something went wrong!");
            }

        } else {
            throw new IllegalArgumentException("Something went wrong!");
        }
    }

    public User getUserByEmail(String email){
        Optional<User> user = userRepository.getUserByEmail(email);
        if(user.isPresent()){
            User _user = user.get();
            return _user;
        } else {
            return null;
        }
    }


}
