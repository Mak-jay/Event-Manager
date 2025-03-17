package com.jay.EventManager.Controller;

import com.jay.EventManager.DTOs.EventDTO;
import com.jay.EventManager.Model.Event;
import com.jay.EventManager.Services.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
@SecurityRequirement(name = "Event Manager")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO){
        EventDTO createEvent = eventService.createEvent(eventDTO);

        return new ResponseEntity<EventDTO>(createEvent,HttpStatus.CREATED);
    }
}
