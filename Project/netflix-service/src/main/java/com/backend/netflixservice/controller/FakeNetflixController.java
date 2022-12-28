package com.backend.netflixservice.controller;

import com.backend.netflixservice.dto.UserRequest;
import com.backend.netflixservice.service.FakeNetflixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/netflix")
@RequiredArgsConstructor
public class FakeNetflixController {

    private final FakeNetflixService fakeNetflixService;

    @PostMapping
    public void validateUser(@RequestBody UserRequest userRequest){
        fakeNetflixService.validateUser(userRequest);
    }
}
