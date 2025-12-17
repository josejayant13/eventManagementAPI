package com.josejayant.EventManagementAPI.controllers;

import com.josejayant.EventManagementAPI.config.JwtFilter;
import com.josejayant.EventManagementAPI.model.Event;
import com.josejayant.EventManagementAPI.services.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EventControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtFilter jwtFilter;

    @MockBean
    EventService eventService;

    @Test
    public void viewAllEvents()throws Exception
    {
        List<Event> events = Arrays.asList(
                new Event(1L, "Tech Conference 2025", "Annual technology conference focusing on AI and Cloud.", "2025-02-12", "Kolkata Convention Center"),
                new Event(2L, "Music Fest", "Live performances by independent artists and DJs.", "2025-04-05", "Ranchi Stadium"),
                new Event(3L, "Startup Pitch Night", "Event for startups to pitch ideas to investors.", "2025-06-18", "Bangalore Innovation Hub")
        );

        when(eventService.viewAll()).thenReturn(new ResponseEntity<>(events, HttpStatus.OK));

        String expectedJson = """
					[
						{
							"event_id": 1,
							"title": "Tech Conference 2025",
							"description": "Annual technology conference focusing on AI and Cloud.",
							"date": "2025-02-12",
							"venue": "Kolkata Convention Center"
						},
						{
							"event_id": 2,
							"title": "Music Fest",
							"description": "Live performances by independent artists and DJs.",
							"date": "2025-04-05",
							"venue": "Ranchi Stadium"
						},
						{
							"event_id": 3,
							"title": "Startup Pitch Night",
							"description": "Event for startups to pitch ideas to investors.",
							"date": "2025-06-18",
							"venue": "Bangalore Innovation Hub"
						}
					]
					  """;

        mockMvc.perform(get("/event/viewAll"))
                .andExpect(status().isOk())
                .andExpect(  content().json(expectedJson));

    }

    @Test
    public void createAnEvent()throws Exception
    {
        String event = """
                {
                    "event_id": 5,
                    "title": "Cultural Fest",
                    "description": "A two-day cultural event featuring dance, music, and art.",
                    "date": "2025-10-25",
                    "venue": "NIT Durgapur Auditorium"
                }
                """;
        when(eventService.createEvent(any()))
                .thenReturn(
                        new ResponseEntity<>("Event created",
                                HttpStatus.CREATED));

        mockMvc.perform(post("/event/createEvent")
                        .contentType("application/json")
                        .content(event))
                        .andExpect(status().isCreated());
    }
}
