package com.scalesampark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalesampark.dto.domain.Participant;

@Repository
public interface ParticipantJPARepository extends JpaRepository<Participant, String> {

}
