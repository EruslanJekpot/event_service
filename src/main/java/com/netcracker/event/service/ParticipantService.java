package com.netcracker.event.service;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.repository.EventRepository;
import com.netcracker.event.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {
    private ParticipantRepository participantRepository;
    private EventRepository eventRepository;

    public ParticipantService(ParticipantRepository participantRepository, EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }

    public Boolean getParticipantStatus(Long id){
        return this.participantRepository.findByParticipantId(id).getIsTeamNeed();
    }

    public Boolean updateParticipantStatus(Participant participant) {
        try {
            participantRepository.updateParticipantStatus(participant.getIsTeamNeed());
            return true;
        }catch (Exception e) {
            System.out.println("Ошибка при изменении статуса участника эввента");
            System.out.println(e);
            return false;
        }
    }

}