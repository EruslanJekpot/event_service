package com.netcracker.event.service;

import com.netcracker.event.dto.EventDto;
import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Organization;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.dto.ParticipantDto;
import com.netcracker.event.repository.EventRepository;
import com.netcracker.event.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
@Slf4j
public class EventService {
    private EventRepository eventRepository;
    private OrganizationRepository organizationRepository;

    public EventService(EventRepository eventRepository, OrganizationRepository organizationRepository) {
        this.eventRepository = eventRepository;
        this.organizationRepository = organizationRepository;
    }

    // Создаём список с айди всех участников эвента
    public List<String> sendParticipantsId(UUID eventId) {
        List<Participant> participants = eventRepository.findByEventId(eventId).getParticipantList();
        List<String> participantsIdList = new ArrayList<>();
        for (Participant participant : participants) {
            participantsIdList.add(participant.getUserId());
        }
        return participantsIdList;
    }

    public HashMap<String, String> getParticipantsName(HashMap attendeesName){
        return attendeesName;
    }

    // Для вывода списка участников эвента
    public List<Participant> getEventParticipantsDto(UUID id) {
        // fetching Event entity object from the database
        Event event = eventRepository.findByEventId(id);
        EventDto eventDto = modelMapper.map(event, EventDto.class);
        return eventDto.getParticipantList();
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

    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    @Autowired
    private ModelMapper modelMapper;

    // Для вывода информации об эвенте с именем и id организации
    public EventDto getEventDto(UUID id) {
        // fetching Event entity object from the database
        Event event = eventRepository.findByEventId(id);
        Organization organization = event.getOrganizationId();
        EventDto eventDto = modelMapper.map(event, EventDto.class);
        eventDto.setOrganizationName(organization.getName());
        return eventDto;
    }

    public byte[] extractBytes(String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", bos);
        return (bos.toByteArray());
    }
}