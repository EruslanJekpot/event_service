package com.netcracker.event.repository;

import com.netcracker.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event,UUID> {
    Event findByEventId(UUID id);
}
