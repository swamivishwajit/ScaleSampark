package com.scalesampark.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalesampark.dto.ParticipantDto;
import com.scalesampark.dto.ParticipantResponseDto;
import com.scalesampark.dto.domain.Participant;
import com.scalesampark.exception.InvalidDataException;
import com.scalesampark.repository.ParticipantJPARepository;

@Service
public class SamparkServiceImpl extends AbstractSamparkService implements SamparkService {

	@Autowired
	ParticipantJPARepository participantJPARepository;
	
	@Override
	public String regieterParticipant(ParticipantDto participentDto) throws InvalidDataException {
		
		Participant p=ParticipantDto.of(participentDto);
		p.setLast_seen(LocalDateTime.now());
		p.setParticipant_uuid(UUID.randomUUID().toString());
		
		Participant participant=participantJPARepository.save(p);
		return participant.getParticipant_uuid();
	}

	@Override
	public List<ParticipantResponseDto> getAllParticipants(String participant_uuid) {
		List<ParticipantResponseDto> response=null;
		Participant participent=participantJPARepository.findById(participant_uuid).orElse(null); 
		if(isValidParticipant(participent)) {
			List<Participant> participents=participantJPARepository.findAll();
			response=participents.stream().map(p->new ParticipantResponseDto(p.getParticipant_uuid(),p.getLast_seen(),p.getNickName())).collect(Collectors.toList());
		}
		return response;
	}

	@Override
	public void deRegisterParticipant(String participant_uuid) {
		Participant participent=participantJPARepository.findById(participant_uuid).orElse(null);
		if(isValidParticipant(participent)) {
			participantJPARepository.delete(participent);
		}
	}
}

