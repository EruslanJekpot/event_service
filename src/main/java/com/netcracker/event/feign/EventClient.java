package com.netcracker.event.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@FeignClient(name = "event", url = "http://localhost:8080")
public interface EventClient {

    @PostMapping(path = "/chat/attendee/names")
    public HashMap<String, String> getParticipantsIdAndNames(String[] usersIdList);
}
