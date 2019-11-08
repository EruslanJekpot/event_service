package com.netcracker.event.controller;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService=eventService;
    }

    @GetMapping(path = "/get/all/e")
    public List<Event> getAll() {
        return this.eventService.getAll();
    }

    @PostMapping(path = "/add/event")
    public void addEvent(@RequestBody Event event){
        eventService.addEvent(event);
    }

    @GetMapping(path = "/get/event/{event_id}")
    public Event getEventById(@PathVariable(value = "event_id") Long eventId){
        return eventService.findByEventId(eventId);
    }

    @GetMapping(path = "/get/event/{event_id}/info")
    public String getEventInfo(@PathVariable(value = "event_id") Long eventId){
        return eventService.getEventInfo(eventId);
    }

    @GetMapping(path = "/get/event/{event_id}/partic")
    public List<Participant> getEventParticipants(@PathVariable(value = "event_id") Long eventId){
        return eventService.getEventParticipants(eventId);
    }
}