package com.josejayant.EventManagementAPI.controllers;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("{event_id}")
    private ResponseEntity<Event> getEventById(@PathVariable int event_id)
    {
        return eventService.getEventById(event_id);
    }

    @DeleteMapping("delete/{event_id}")
    private ResponseEntity<Void> deleteEventById(@PathVariable int event_id)
    {
        return eventService.deleteEventById(event_id);
    }
}
