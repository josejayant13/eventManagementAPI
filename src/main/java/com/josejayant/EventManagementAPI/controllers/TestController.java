package com.josejayant.EventManagementAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/")
    public String test(){
        return "Hello World";
    }

    @GetMapping("/endpoint1")
    public String test2(){
        return "This is endpoint1";
    }

    @GetMapping("/endpoint2")
    public String test3(){
        return "This is endpoint2";
    }

}
