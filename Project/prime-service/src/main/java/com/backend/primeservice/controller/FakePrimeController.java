package com.backend.primeservice.controller;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import com.backend.primeservice.dto.AuthUserRequest;
import com.backend.primeservice.service.FakeAmazonService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/prime")
@RequiredArgsConstructor
public class FakePrimeController {
    private final FakeAmazonService fakeAmazonService;
    
    @PostMapping("/")
    public Boolean validateUser(@RequestBody AuthUserRequest userRequest){
       return  fakeAmazonService.validateUser(userRequest);
    }

    @GetMapping(value = "/video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range) {
        System.out.println("range in bytes() : " + range);
        return fakeAmazonService.getFilm(title);
    }
}
