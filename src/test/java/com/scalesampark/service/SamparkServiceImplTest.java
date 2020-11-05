package com.scalesampark.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.scalesampark.dto.ParticipantDto;
import com.scalesampark.dto.ParticipantResponseDto;
import com.scalesampark.dto.domain.Participant;
import com.scalesampark.exception.InvalidDataException;
import com.scalesampark.exception.ParticipentNotFoundException;
import com.scalesampark.repository.ParticipantJPARepository;

@SpringBootTest
public class SamparkServiceImplTest {
	
	@Mock
	ParticipantJPARepository participantJPARepository;
	@InjectMocks
	SamparkServiceImpl samparkService;

	@Test
	public void test_InvalidDateNotAccepted()
	{
		Participant p=new Participant();
		ParticipantDto dto=new ParticipantDto();
		when(participantJPARepository.save(p)).thenReturn(p);
		InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
			samparkService.regieterParticipant(dto);
	    });
		
		assertEquals("Empty registration details", exception.getMessage());
	}
	
	@Test
	public void testGetAllParticipants() {
		String uuid=UUID.randomUUID().toString();
		List<Participant> list=new ArrayList<>();
		Participant participant=new Participant();
		participant.setLast_seen(LocalDateTime.now());
		participant.setEmail("ABC@gmail.com");
		participant.setNickName("ABC");
		participant.setParticipant_uuid(uuid);
		list.add(participant);
		when(participantJPARepository.findById(uuid)).thenReturn(Optional.of(participant));
		when(participantJPARepository.findAll()).thenReturn(list);
		List<ParticipantResponseDto> responseFromService=samparkService.getAllParticipants(uuid);
		assertNotNull(responseFromService);
	}
	@Test
	public void testGetAllParticipantsInvalidParticipanyExceptionTest() {
		String uuid=UUID.randomUUID().toString();
		Participant participant=new Participant();
		participant.setLast_seen(LocalDateTime.now());
		participant.setEmail("ABC@gmail.com");
		participant.setNickName("ABC");
		participant.setParticipant_uuid(uuid);
		
		when(participantJPARepository.findById("ABC")).thenReturn(Optional.of(participant));
		ParticipentNotFoundException exception = assertThrows(ParticipentNotFoundException.class, () -> {
			samparkService.getAllParticipants(uuid);
	    });
		
		assertEquals("Paricipent with given id not found", exception.getMessage());
	}
	@Test
	public void testParticipantDeregistration_When_Vvalid_Id_provided() {
		String uuid=UUID.randomUUID().toString();
		Participant participant=new Participant();
		participant.setLast_seen(LocalDateTime.now());
		participant.setEmail("ABC@gmail.com");
		participant.setNickName("ABC");
		participant.setParticipant_uuid(uuid);
		
		when(participantJPARepository.findById(uuid)).thenReturn(Optional.of(participant));
		samparkService.deRegisterParticipant(uuid);
	}
	
	@Test
	public void testParticipantDeregistration_When_invalid_Id_provided() {
		String uuid=UUID.randomUUID().toString();
		Participant participant=new Participant();
		participant.setLast_seen(LocalDateTime.now());
		participant.setEmail("ABC@gmail.com");
		participant.setNickName("ABC");
		participant.setParticipant_uuid(uuid);
		
		when(participantJPARepository.findById("ABC")).thenReturn(Optional.of(participant));
		ParticipentNotFoundException exception = assertThrows(ParticipentNotFoundException.class, () -> {
			samparkService.deRegisterParticipant(uuid);
	    });
		assertEquals("Paricipent with given id not found", exception.getMessage());
	}
}
