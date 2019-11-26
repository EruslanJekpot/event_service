package com.netcracker.event.controller;

import com.netcracker.event.domain.Event;
import com.netcracker.event.domain.Participant;
import com.netcracker.event.service.EventService;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@EnableAutoConfiguration
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService=eventService;
    }

    @GetMapping(path = "/get/all/event")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(eventService.findAllByStartDateAfter());
    }

    //dlya proverki (roflo metod)
    @PostMapping(path = "/easySave/event")
    public ResponseEntity easySaveEvent(Event event, MultipartFile multipartFile)
    {
        byte[] image = null;
        try {
            if (multipartFile!=null) {
                image = multipartFile.getBytes();
            } else  image = extractBytes(".event.jpeg");
        } catch (Exception exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error loading image");
        }
        eventService.easySaveEvent(event);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/update/event")
    public ResponseEntity updateEvent(Event event, @RequestParam("image") MultipartFile multipartFile)
    {
        event.setStartDate(event.getStartDate());
        event.setInfo(event.getInfo());
        event.setCity(event.getCity());
        event.setPrize(event.getPrize());
        event.setName(event.getName());
        event.setEventType(event.getEventType());
        event.setMaxMemQuantity(event.getMaxMemQuantity());
        event.setOrganizationList(event.getOrganizationList());
        byte[] image = null;
        try {
            if (multipartFile!=null)    image = multipartFile.getBytes();
        } catch (Exception exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error loading image");
        }
        eventService.updateEvent(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/event/{event_id}")
    public ResponseEntity getEventById(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.findByEventId(eventId));
    }

    @GetMapping(path = "/get/event/{event_id}/info")
    public ResponseEntity getEventInfo(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.getEventInfo(eventId));
    }

    @GetMapping(path = "/get/event/{event_id}/participant")
    public ResponseEntity getEventParticipants(@PathVariable(value = "event_id") UUID eventId){
        return ResponseEntity.ok().body(eventService.getEventParticipants(eventId));
    }

    public byte[] extractBytes (String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
        return ( data.getData() );
    }
}