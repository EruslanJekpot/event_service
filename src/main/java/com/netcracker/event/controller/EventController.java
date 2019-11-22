package com.netcracker.event.controller;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService=eventService;
    }

    @GetMapping(path = "/get/all/event")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(eventService.findAllByStartDateAfter());
    }

    @PostMapping(path = "/organization/{organization_id}/event")
    public ResponseEntity saveEvent(@PathVariable("organization_id") UUID organizationId,
                                    @RequestBody Event event,
                                    @RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                byte[] bytes = image.getBytes();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        eventService.saveEvent(organizationId, event);
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