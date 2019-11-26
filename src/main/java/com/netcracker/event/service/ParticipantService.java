package com.netcracker.event.service;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.repository.EventRepository;
import com.netcracker.event.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ParticipantService {
    private ParticipantRepository participantRepository;
    private EventRepository eventRepository;

    public ParticipantService(ParticipantRepository participantRepository, EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void saveParticipant(Participant participant){
        participantRepository.save(participant);
    }

    public Participant getParticipantByUserId(String userId) {
        return participantRepository.findParticipantByUserId(userId);
    }
}