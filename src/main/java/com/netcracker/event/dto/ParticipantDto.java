package com.netcracker.event.dto;

import lombok.Data;

@Data
public class ParticipantDto {
    private String fullName;
    private String userId;
    private boolean isTeamNeed;
    private String eventId;
}
