package com.backend.googleservice.controller;

import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping("/api/googleAuth")
public class GoogleController {

    @GetMapping()
    public String prova(){
        return "grande!!!";
    }
    @GetMapping("/user")
    public Principal userAuth(Principal principal){
        System.out.println(principal.getName());
        return principal;
    }

}
