package com.backend.netflixservice.controller;

import com.backend.netflixservice.dto.AuthUserRequest;
import com.backend.netflixservice.service.FakeNetflixService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/netflix")
@RequiredArgsConstructor
public class FakeNetflixController {

    private final FakeNetflixService fakeNetflixService;

    @PostMapping("/")
    public Boolean validateUser(@RequestBody AuthUserRequest userRequest){
        return fakeNetflixService.validateUser(userRequest);
    }

    @GetMapping(value = "video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range) {
        System.out.println("range in bytes() : " + range);
        return fakeNetflixService.getFilm(title);
    }
}
