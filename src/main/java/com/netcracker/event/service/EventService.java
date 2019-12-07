package com.netcracker.event.service;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Organization;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.dto.EventDto;
import com.netcracker.event.dto.ParticipantDto;
import com.netcracker.event.feign.EventClient;
import com.netcracker.event.repository.EventRepository;
import com.netcracker.event.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
@Slf4j
public class EventService {
    private EventRepository eventRepository;
    private OrganizationRepository organizationRepository;
    private EventClient eventClient;

    public EventService(EventRepository eventRepository, OrganizationRepository organizationRepository, EventClient eventClient) {
        this.eventRepository = eventRepository;
        this.organizationRepository = organizationRepository;
        this.eventClient = eventClient;
    }

    @Autowired
    private ModelMapper modelMapper;

    // Для вывода списка участников эвента
    public List<ParticipantDto> getEventParticipantsDto(UUID eventId) {
        List<Participant> participants = eventRepository.findByEventId(eventId).getParticipantList();
        List<String> participantsIdList = new ArrayList<>();
        for (Participant participant : participants) {
            participantsIdList.add(participant.getUserId());
        }
        HashMap<String, String> participantsIdAndNames = eventClient.getParticipantsIdAndNames(participantsIdList.toArray(new String[0]));
        List<ParticipantDto> participantDtoList = new ArrayList<>();
        for (Participant participant : participants) {
            Participant par = new Participant();
            par.setTeamNeed(participant.isTeamNeed());
            par.setEventId(participant.getEventId());
            par.setUserId(participant.getUserId());
            ParticipantDto participantDto = modelMapper.map(par, ParticipantDto.class);
            participantDto.setFullName(participantsIdAndNames.get(par.getUserId()));
            participantDtoList.add(participantDto);
        }
        return participantDtoList;
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