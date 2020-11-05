package com.scalesampark.dto.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Participant")
public class Participant {
	
	@Id
	private String participant_uuid;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String nickName;
	@Column(nullable  =false)
	LocalDateTime last_seen;
	
	
	public String getParticipant_uuid() {
		return participant_uuid;
	}
	public void setParticipant_uuid(String participant_uuid) {
		this.participant_uuid = participant_uuid;
	}
	public LocalDateTime getLast_seen() {
		return last_seen;
	}
	public void setLast_seen(LocalDateTime last_seen) {
		this.last_seen = last_seen;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
