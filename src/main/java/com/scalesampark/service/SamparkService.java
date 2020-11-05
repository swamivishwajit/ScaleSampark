package com.scalesampark.service;

import java.util.List;

import com.scalesampark.dto.ParticipantDto;
import com.scalesampark.dto.ParticipantResponseDto;
import com.scalesampark.exception.InvalidDataException;

public interface SamparkService {
	public String regieterParticipant(ParticipantDto participentDto) throws InvalidDataException ;
	public List<ParticipantResponseDto> getAllParticipants(String id);
	public void deRegisterParticipant(String participant_uuid);

}
