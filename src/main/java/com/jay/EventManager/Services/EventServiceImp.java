package com.jay.EventManager.Services;

import org.modelmapper.ModelMapper;

import com.jay.EventManager.DTOs.EventDTO;
import com.jay.EventManager.Model.Event;
import com.jay.EventManager.Model.User;
import com.jay.EventManager.Repositories.EventRepo;
import com.jay.EventManager.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImp implements EventService{

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepo.findById(email).get();

        Event event = modelMapper.map(eventDTO, Event.class);
        event.setUser(user);

        int startDay = event.getStartDate().getDayOfMonth();
        int endDay = event.getEndDate().getDayOfMonth();

        if (startDay - endDay == 0) {
            event.setEventType("Non-recurring");
        } else {
            event.setEventType("Recurring");
        }

        Event createdEvent = eventRepo.save(event);

        return modelMapper.map(createdEvent, EventDTO.class);
    }
}
