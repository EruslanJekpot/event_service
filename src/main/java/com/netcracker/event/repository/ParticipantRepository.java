package com.netcracker.event.repository;

import com.netcracker.event.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    Participant findByParticipantId(Long id);
    @Modifying
    @Transactional
    @Query("update Participant participant set participant.participantId =:participant_id, participant.isTeamNeed =:isTeamNeed where participant.participantId = :participant_id")
    void updateParticipantStatus(@Param("isTeamNeed") Boolean isTeamNeed);
}
