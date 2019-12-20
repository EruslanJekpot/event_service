package com.netcracker.event.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@FeignClient(name = "event", url = "https://chatmicroservice.herokuapp.com/")
public interface EventClient {

    @PostMapping(path = "/chat/attendee/names")
    public HashMap<String, String> getParticipantsIdAndNames(String[] usersIdList);
}
