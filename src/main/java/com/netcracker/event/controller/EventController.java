package com.netcracker.event.controller;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService=eventService;
    }

    @GetMapping(path = "/get/all/event")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(eventService.findAllByStartDateAfter());
    }

    @PostMapping(path = "/save/event")
    public ResponseEntity saveEvent(@RequestBody Event event){
        eventService.saveEvent(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/event/{event_id}")
    public ResponseEntity getEventById(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.findByEventId(eventId));
    }

    @GetMapping(path = "/get/event/{event_id}/info")
    public ResponseEntity getEventInfo(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.getEventInfo(eventId));
    }

    @GetMapping(path = "/get/event/{event_id}/participant")
    public ResponseEntity getEventParticipants(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.getEventParticipants(eventId));
    }
}