package com.backend.primeservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.primeservice.dto.AuthUserRequest;
import com.backend.primeservice.service.FakeAmazonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/prime")
@RequiredArgsConstructor
public class FakePrimeController {
    private final FakeAmazonService fakeAmazonService;
    
    @PostMapping
    public Boolean validateUser(@RequestBody AuthUserRequest userRequest){
       return  fakeAmazonService.validateUser(userRequest);
    }
     
}
