package com.backend.userservice.controller;

import com.backend.userservice.dto.*;
import com.backend.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void userCreate(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }



    @PostMapping("/updateEmail")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmail(@RequestBody EmailUpdateRequest emailUpdateRequest){
        userService.updateEmail(emailUpdateRequest);
    }

    @PostMapping("/updatePassword")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest){
        userService.updatePassword(passwordUpdateRequest);
    }

    @PostMapping("/unsubscribe")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void unsubscribeUser(@RequestBody UnsubscribeUserRequest unsubscribeUserRequest){
        userService.unsubscribeUser(unsubscribeUserRequest);
    }
}
