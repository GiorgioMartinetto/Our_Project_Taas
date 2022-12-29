package com.backend.userservice.service;

import com.backend.userservice.dto.AuthUserRequest;
import com.backend.userservice.dto.SubscriptionAuthDto;
import com.backend.userservice.model.Subscription;
import com.backend.userservice.model.User;
import com.backend.userservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;


@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final UserService userService;
    private final WebClient.Builder webClientBuilder;



    public void authenticationSubscription(SubscriptionAuthDto subscriptionAuthDto) throws AuthenticationException {
        String uri;
        AuthUserRequest authUserRequest = AuthUserRequest.builder()
                        .email(subscriptionAuthDto.getEmailPlatform())
                        .password(subscriptionAuthDto.getPassword())
                        .build();

        switch (subscriptionAuthDto.getPlatform()){
            case "netflix":
                uri = "http://netflix-service/api/netflix";
                break;
            case "prime":
                uri = "http://prime-service/api/prime";
                break;
            default:
                throw new AuthenticationException("Invalid Platform Value ...");

        }

         boolean control = Boolean.TRUE.equals(webClientBuilder.build().post()
                 .uri(uri)
                 .body(Mono.just(authUserRequest), AuthUserRequest.class)
                 .retrieve()
                 .bodyToMono(Boolean.class)
                 .block());


        if(control){
            System.out.println(subscriptionAuthDto.getUserName());
            User user = userService.getUserByUser(subscriptionAuthDto.getUserName());
            if(user != null){
                System.out.println("Authentication OK ...");
                Subscription subscription = Subscription.builder()
                        .user(user)
                        .platform(subscriptionAuthDto.getPlatform())
                        .build();
                subscriptionRepository.save(subscription);
            } else {
                System.out.println("Authentication FAIL: User is NULL ...");
            }
        } else {
            System.out.println("Authentication FAIL: control is false ...");
        }
    }
}
