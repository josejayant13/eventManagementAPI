package com.josejayant.EventManagementAPI.controllers;

import com.josejayant.EventManagementAPI.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josejayant.EventManagementAPI.model.User;
import com.josejayant.EventManagementAPI.services.ApiService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class ApiController{

    @Autowired
    private ApiService apiService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        System.out.println("Login Method called");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()){
            System.out.println("Login Success");
            return jwtService.generateJwtToken(user.getUsername());
        }

        else
            return "login failed";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        System.out.println("Register Controller");
        User registeredUser = apiService.register(user);
        System.out.println(registeredUser.toString());
        return registeredUser;
    }

}