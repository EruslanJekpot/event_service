package com.netcracker.event.service;

import com.netcracker.event.Dto.EventDto;
import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Organization;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.repository.EventRepository;
import com.netcracker.event.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
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

    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    @Autowired
    private ModelMapper modelMapper;

    public EventDto getEventDto(UUID id) {

        // fetching Event entity object from the database
        Event event = eventRepository.findByEventId(id);
        Organization organization = event.getOrganizationId();
        EventDto eventDto = modelMapper.map(event, EventDto.class);
        eventDto.setOrganizationName(organization.getName());
        return eventDto;
    }

    public byte[] extractBytes (String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
        return ( data.getData() );
    }
}