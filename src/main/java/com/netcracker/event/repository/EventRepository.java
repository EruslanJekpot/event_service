package com.netcracker.event.repository;

import com.netcracker.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event,UUID> {
    Event findByEventId(UUID id);

    @Query("select e from Event e where e.startDate > :currentDate")
    List<Event> findEventsAfterCertainDate(@Param(value = "currentDate") Date currentDate);
}
