package com.netcracker.event.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.UUID;

@FeignClient(name = "event", url = "http://localhost:8091")
public interface EventClient {

    @GetMapping(path = "/attendee/names")
    public ResponseEntity getParticipantsName(HashMap attendeesName);
}
