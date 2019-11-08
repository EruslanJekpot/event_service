package com.netcracker.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "organization")
@NoArgsConstructor
public class Organization {
    @Id
    @Column(name = "org_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;
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
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "event_org",
            joinColumns = @JoinColumn(name = "org_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> eventList;
}