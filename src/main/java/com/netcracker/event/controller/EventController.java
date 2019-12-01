package com.netcracker.event.controller;

import com.netcracker.event.Dto.EventDto;
import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Organization;
import com.netcracker.event.service.EventService;
import com.netcracker.event.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@EnableAutoConfiguration
public class EventController {
    private EventService eventService;
    private OrganizationService organizationService;

    public EventController(EventService eventService, OrganizationService organizationService) {
        this.eventService = eventService;
        this.organizationService = organizationService;
    }

    @GetMapping(path = "/get/all/event")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(eventService.findAllByStartDateAfter());
    }

    //dlya proverki (roflo metod)
    @PostMapping(path = "/easySave/event")
    public ResponseEntity easySaveEvent(@RequestBody Event event) {
        eventService.saveEvent(event);
        return ResponseEntity.ok().build();
    }

    //с добавлением в список
    @PostMapping(path = "/save/event")
    public ResponseEntity saveEvent(@RequestHeader("uid") String userId, @RequestBody Event event) {
        Organization organization = organizationService.getOrganizationByUser(userId);
        event.setOrganizationId(organization);
        eventService.saveEvent(event);
        return ResponseEntity.ok().build();
    }

    @PatchMapping( path = "/update/event")
    public ResponseEntity updateEvent(@RequestBody Event event) {
        eventService.updateEvent(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/event/{event_id}")
    public ResponseEntity getEventById(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.findByEventId(eventId));
    }

    // dto с организацией и эвентом
    @GetMapping(path = "/eventDto/{event_id}")
    public ResponseEntity<EventDto> getEventDto(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.getEventDto(eventId));
    }

    @GetMapping(path = "/get/event/{event_id}/info")
    public ResponseEntity getEventInfo(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.getEventInfo(eventId));
    }

    @GetMapping(path = "/get/event/{event_id}/participants")
    public ResponseEntity getEventParticipants(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.getEventParticipants(eventId));
    }

    @GetMapping(path = "/eventDto/{event_id}/participants")
    public ResponseEntity<List> getEventParticipantsDto(@PathVariable(value = "event_id") UUID eventId) {
        return ResponseEntity.ok().body(eventService.getEventParticipantsDto(eventId).getParticipantList());
    }
}