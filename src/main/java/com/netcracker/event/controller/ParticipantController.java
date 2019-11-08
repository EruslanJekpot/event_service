package com.netcracker.event.controller;

import com.netcracker.event.domain.Participant;
import com.netcracker.event.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParticipantController {
    private ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService){
        this.participantService = participantService;
    }

    @GetMapping(path = "/partic/{participant_id}/status")
    public Boolean getParticipantStatus(@PathVariable(value = "participant_id") Long participantId){
        return participantService.getParticipantStatus(participantId);
    }

    @PatchMapping(path = "/partic/update")
    public void updateParticipantStatus(@RequestBody Participant participant){
        participantService.updateParticipantStatus(participant);
    }
}