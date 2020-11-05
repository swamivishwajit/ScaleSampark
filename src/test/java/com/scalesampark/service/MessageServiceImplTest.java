package com.scalesampark.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.scalesampark.dto.MessageDto;
import com.scalesampark.dto.domain.Message;
import com.scalesampark.dto.domain.Participant;
import com.scalesampark.encryptdecrypt.CryptoGraphy;
import com.scalesampark.exception.MessageNotFoundException;
import com.scalesampark.exception.ParticipentNotFoundException;
import com.scalesampark.repository.MessageJPARepository;
import com.scalesampark.repository.ParticipantJPARepository;
import com.scalesampark.types.MessageType;

@SpringBootTest
public class MessageServiceImplTest {
	@Mock
	ParticipantJPARepository participantJPARepository;
	
	@Mock
	MessageJPARepository messageRepository;
	
	@InjectMocks
	MessageServiceImpl messageService;
	
	@Test
	public void testSaveMessageInvalidUserId() {
		String uuid=UUID.randomUUID().toString();
		Participant participant=new Participant();
		participant.setLast_seen(LocalDateTime.now());
		participant.setEmail("ABC@gmail.com");
		participant.setNickName("ABC");
		participant.setParticipant_uuid(uuid);
		
		when(participantJPARepository.findById("ABC")).thenReturn(Optional.of(participant));
		ParticipentNotFoundException exception = assertThrows(ParticipentNotFoundException.class, () -> {
			messageService.saveMessage(uuid, null);
	    });
		assertEquals("Paricipent with given id not found", exception.getMessage());
	}
	
	@Test
	public void testFetchPendingMessageWhenInvalidUserId() {
		String uuid=UUID.randomUUID().toString();
		Participant participant=new Participant();
		participant.setLast_seen(LocalDateTime.now());
		participant.setEmail("ABC@gmail.com");
		participant.setNickName("ABC");
		participant.setParticipant_uuid(uuid);
		
		when(participantJPARepository.findById("ABC")).thenReturn(Optional.of(participant));
		ParticipentNotFoundException exception = assertThrows(ParticipentNotFoundException.class, () -> {
			messageService.fetchPendingMessages(uuid);
	    });
		assertEquals("Paricipent with given id not found", exception.getMessage());
	}
	
	@Test
	public void testFetchPendingMessageWhenValidUserId() {
		String uuid=UUID.randomUUID().toString();
		Participant participant=new Participant();
		participant.setLast_seen(LocalDateTime.now());
		participant.setEmail("ABC@gmail.com");
		participant.setNickName("ABC");
		participant.setParticipant_uuid(uuid);
		participant.setLast_seen(LocalDateTime.now());
		
		Message m=new Message();
		m.setMessage("ABC");
		m.setMessage_type(MessageType.TEXT);
		m.setParticipant(participant);
		m.setMessage_uuid(UUID.randomUUID().toString());
		List<Message> messages=new ArrayList<Message>();
		messages.add(m);
		//when(CryptoGraphy.decryptMessage(any(String.class), any(String.class))).thenReturn("ABC");
		//when(LocalDateTime.now()).thenReturn(participant.getLast_seen());
		when(participantJPARepository.findById(uuid)).thenReturn(Optional.of(participant));
		when(participantJPARepository.save(participant)).thenReturn(participant);
		
		when(messageRepository.findAll()).thenReturn(messages);
		List<MessageDto> response=messageService.fetchPendingMessages(uuid);
		assertNotNull(response);
	}
	
	@Test
	public void testFetchPendingMessageWhenNoMessagesPresent() {
		String uuid=UUID.randomUUID().toString();
		Participant participant=new Participant();
		participant.setLast_seen(LocalDateTime.now());
		participant.setEmail("ABC@gmail.com");
		participant.setNickName("ABC");
		participant.setParticipant_uuid(uuid);
		participant.setLast_seen(LocalDateTime.now());
		
		Message m=new Message();
		/*m.setMessage("ABC");
		m.setMessage_type(MessageType.TEXT);
		m.setParticipant(participant);
		m.setMessage_uuid(UUID.randomUUID().toString());>*/
		List<Message> messages=new ArrayList<Message>();
		//when(CryptoGraphy.decryptMessage(any(String.class), any(String.class))).thenReturn("ABC");
		//when(LocalDateTime.now()).thenReturn(participant.getLast_seen());
		when(participantJPARepository.findById(uuid)).thenReturn(Optional.of(participant));
		when(participantJPARepository.save(participant)).thenReturn(participant);
		
		when(messageRepository.findAll()).thenReturn(messages);
		//List<MessageDto> response=messageService.fetchPendingMessages(uuid);
		
		MessageNotFoundException exception = assertThrows(MessageNotFoundException.class, () -> {
			messageService.fetchPendingMessages(uuid);
	    });
		assertEquals("No Pending Messages Found",exception.getMessage());
	}
	
	
	
	
	
	
	
	
	
	
	

}
