package com.netcracker.event.service;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.repository.EventRepository;
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

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAllByStartDateAfter() {
        //List<Event> events = eventRepository.findAll();
        Date date = new Date();
        //System.out.println(date);
//        for (Event event : events) {
//            if (event.getStartDate() ) events
//        }
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

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }
}