package com.netcracker.event.service;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Organization;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.repository.EventRepository;
import com.netcracker.event.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class EventService {
    private EventRepository eventRepository;
    private OrganizationRepository organizationRepository;

    public EventService(EventRepository eventRepository, OrganizationRepository organizationRepository) {
        this.eventRepository = eventRepository;
        this.organizationRepository = organizationRepository;
    }

    public List<Event> findAllByStartDateAfter() {
        Date date = new Date();
        return eventRepository.findEventsAfterCertainDate(date);
    }


    public Event findByEventId(UUID id) {
        return eventRepository.findByEventId(id);
    }

    public String getEventInfo(UUID id) {
        return eventRepository.findByEventId(id).getInfo();
    }

    public List<Participant> getEventParticipants(UUID id) {
        return eventRepository.findByEventId(id).getParticipantList();
    }

    public void saveEvent(UUID organizationId, Event event) {
        Organization organization = organizationRepository.findByOrganizationId(organizationId);
        organization.getEventList().add(event);
        eventRepository.save(event);
    }


}