package com.netcracker.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "participant")
@NoArgsConstructor
public class Participant{
    @Id
    @Column(name = "participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    @ManyToOne(fetch = FetchType.EAGER)
    //data transfer object for participant
    @JoinColumn(name = "event_id")
    @NonNull
    private Event eventId;
    @Column(name = "attendee_id")
    private String attendeeId;
    @Column(name = "isTeamNeed")
    private Boolean isTeamNeed;
}