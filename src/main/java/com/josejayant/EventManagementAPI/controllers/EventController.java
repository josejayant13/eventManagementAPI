package com.josejayant.EventManagementAPI.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.josejayant.EventManagementAPI.model.Event;
import com.josejayant.EventManagementAPI.services.EventService;

@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    EventService eventService;
    
    @PostMapping("createEvent")
    private ResponseEntity<String> createEvent(@RequestBody Event event){
        System.out.println("EventController.createEvent");
        return eventService.createEvent(event);
    }

    @GetMapping("viewAll")
    private ResponseEntity<List<Event>> viewAll(){
        return eventService.viewAll();
    }
}
