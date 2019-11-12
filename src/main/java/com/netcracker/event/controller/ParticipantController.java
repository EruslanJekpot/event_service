package com.netcracker.event.controller;

import com.netcracker.event.domain.Participant;
import com.netcracker.event.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ParticipantController {
    private ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService){
        this.participantService = participantService;
    }

    @GetMapping(path = "/participant/{participant_id}/status")
    public ResponseEntity<Boolean> getParticipantStatus(@PathVariable(value = "participant_id") UUID participantId){
        return ResponseEntity.ok().body(participantService.getParticipantStatus(participantId));
    }

    @PatchMapping(path = "/update/participant")
    public ResponseEntity updateParticipantStatus(@RequestBody Participant participant){
        participantService.updateParticipantStatus(participant);
        return ResponseEntity.ok().build();
    }
}