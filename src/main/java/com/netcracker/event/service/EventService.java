package com.netcracker.event.service;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EventService {
    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public List<Event> getAll(){
        return this.eventRepository.findAll();
    }

    public Event findByEventId(Long id){
        return this.eventRepository.findByEventId(id);
    }

    //??
    public String getEventInfo(Long id){
        return this.eventRepository.findByEventId(id).getInfo();
    }

    public List<Participant> getEventParticipants(Long id){
        return this.eventRepository.findByEventId(id).getParticipantList();
    }

    public Boolean addEvent(Event event) {
        try {
            eventRepository.save(event);
            return true;
        }catch (Exception e) {
            System.out.println("Ошибка при сохранении эвента");
            return false;
        }
    }
}