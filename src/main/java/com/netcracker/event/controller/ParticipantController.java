package com.netcracker.event.controller;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.service.EventService;
import com.netcracker.event.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ParticipantController {
    private final ParticipantService participantService;
    private final EventService eventService;

    @GetMapping(path = "/participant/status")
    public ResponseEntity getParticipantStatus(@RequestHeader(value = "uid") String userId, @RequestParam String eventId){
        Event event = eventService.findByEventId(UUID.fromString(eventId));
        Participant participant = participantService.getParticipantOfEvent(event, userId);
        return ResponseEntity.ok().body(participant);
    }

    @PatchMapping(path = "/update/participant")
    public ResponseEntity updateParticipantStatus(@RequestHeader("uid") String userId, @RequestBody Participant par) {
        Event event = eventService.findByEventId(par.getEventId().getEventId());
        Participant participant = participantService.getParticipantOfEvent(event, userId);
        if (participant == null) {
            participant = new Participant(event, userId, par.isTeamNeed());
        } else {
            participant.setTeamNeed(par.isTeamNeed());
        }
        participantService.saveParticipant(participant);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/participant")
    public ResponseEntity deleteParticipantStatus(@RequestHeader("uid") String userId, @RequestParam String eventId) {
        Event event = eventService.findByEventId(UUID.fromString(eventId));
        Participant participant = participantService.getParticipantOfEvent(event, userId);
        if (participant != null) {
            participantService.deleteParticipant(participant);
        }
        return ResponseEntity.ok().build();
    }
}