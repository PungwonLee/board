package com.example.board.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/test/api")
public class TestController {

    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(Principal principal) {
        System.out.println(principal.getName());
        return principal.getName();
    }
    @GetMapping("/username2")
    @ResponseBody
    public String currentUserName(Authentication authentication)
    {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());

        return userDetails.getUsername();
    }

    @GetMapping("/username3")
    @ResponseBody
    public String currentUserName( @AuthenticationPrincipal User user)
    {
        System.out.println(user.getUsername());

        return user.getUsername();
    }
}