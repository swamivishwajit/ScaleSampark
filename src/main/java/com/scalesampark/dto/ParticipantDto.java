package com.scalesampark.dto;

import com.scalesampark.dto.domain.Participant;
import com.scalesampark.exception.InvalidDataException;

public class ParticipantDto {
	private String email;
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
	private String nickName;
	public static Participant of(ParticipantDto dto) throws InvalidDataException {
		if(null!=dto && (null!=dto.getEmail() && null!=dto.getNickName())) {
			Participant p=new Participant();
			p.setEmail(dto.getEmail());
			p.setNickName(dto.getNickName());
			return p;
		}
		else {
			throw new InvalidDataException("Empty registration details");
		}
	}

}
