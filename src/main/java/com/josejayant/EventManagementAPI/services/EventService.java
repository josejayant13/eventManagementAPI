package com.josejayant.EventManagementAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.josejayant.EventManagementAPI.model.Event;
import com.josejayant.EventManagementAPI.repositories.EventRepo;

@Service
public class EventService {

    @Autowired
    EventRepo eventRepo;

    public ResponseEntity<String> createEvent(Event event) {
        System.out.println("EventService.createEvent");
        Long createEventId = eventRepo.save(event).getEvent_id();

        if(createEventId > 0)
        return new ResponseEntity<>("Event created with id = " + createEventId, HttpStatus.CREATED);
        else
        return new ResponseEntity<>("Event creation failed", HttpStatus.CONFLICT);
    }

    public ResponseEntity<List<Event>> viewAll(){
        return new ResponseEntity<>(eventRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Event> getEventById(int event_id) {

        Optional<Event> event = eventRepo.findById(event_id);
        if(event.isPresent())
            return new ResponseEntity<>(event.get(), HttpStatus.FOUND);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        
    }

    public ResponseEntity<Void> deleteEventById(int event_id) {
        if(getEventById(event_id).getStatusCode() == HttpStatus.FOUND)
        {
            eventRepo.deleteById(event_id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }

}
