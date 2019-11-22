package com.netcracker.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "organization")
@NoArgsConstructor
public class Organization {
    @Id
    @Column(name = "organization_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy ="org.hibernate.id.UUIDGenerator")
    private UUID organizationId;
    @Column(name = "name", unique = true)
    @NonNull
    private String name;
    @Column(name = "email", unique = true)
    @NonNull
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "info")
    private String info;
    @Column(name = "user_id")
    @NonNull
    private String userId;
    @Column(name = "image")
    private byte[] image;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "event_organization",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> eventList;
}