package com.backend.userservice.controller;


import com.backend.userservice.dto.SubscriptionAuthDto;
import com.backend.userservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/authentication")
    public void authenticationSubscription(@RequestBody SubscriptionAuthDto subscriptionAuthDto){
        try{
            subscriptionService.authenticationSubscription(subscriptionAuthDto);
        } catch (Exception e){

        }
    }
}
