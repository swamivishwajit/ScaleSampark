package com.scalesampark.dto;

import java.time.LocalDateTime;

public class ParticipantResponseDto {

	public ParticipantResponseDto(String participant_uuid, LocalDateTime last_seen, String nickName) {
		super();
		this.participant_uuid = participant_uuid;
		this.last_seen = last_seen;
		this.nickName = nickName;
	}
	private String participant_uuid;
	
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
	private LocalDateTime last_seen;
	/*private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}*/
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	private String nickName;

}
