package com.netcracker.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "participant")
@NoArgsConstructor
public class Participant {
    @Id
    @Column(name = "participant_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy ="org.hibernate.id.UUIDGenerator")
    private UUID participantId;
    @ManyToOne(fetch = FetchType.LAZY)
    //data transfer object for participant
    @JoinColumn(name = "event_id")
    @NonNull
    private Event eventId;
    @Column(name = "user_id")
    @NonNull
    private String userId;
    @Column(name = "isTeamNeed")
    private Boolean isTeamNeed;
}