package com.scalesampark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalesampark.dto.domain.Message;

@Repository
public interface MessageJPARepository extends JpaRepository<Message, String> {

}
