package com.netcracker.event.controller;

import com.netcracker.event.domain.Participant;
import com.netcracker.event.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParticipantController {
    private ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService){
        this.participantService = participantService;
    }

    @GetMapping(path = "/participant/status")
    public ResponseEntity<Boolean> getParticipantStatus(@RequestHeader(value = "uid") String userId){
        Participant participant = participantService.getParticipantByUserId(userId);
        return ResponseEntity.ok().body(participant.getIsTeamNeed());
    }

    @PatchMapping(path = "/update/participant")
    public ResponseEntity updateParticipantStatus(@RequestHeader("uid") String userId, @RequestBody Participant par) {
        Participant participant = participantService.getParticipantByUserId(userId);
        participant.setIsTeamNeed(par.getIsTeamNeed());
        participantService.saveParticipant(participant);
        return ResponseEntity.ok().build();
    }
}