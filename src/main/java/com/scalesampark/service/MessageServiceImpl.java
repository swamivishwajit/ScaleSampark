package com.scalesampark.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scalesampark.dto.MessageDto;
import com.scalesampark.dto.domain.Message;
import com.scalesampark.dto.domain.Participant;
import com.scalesampark.encryptdecrypt.CryptoGraphy;
import com.scalesampark.exception.InvalidDataException;
import com.scalesampark.exception.MessageNotFoundException;
import com.scalesampark.repository.MessageJPARepository;
import com.scalesampark.repository.ParticipantJPARepository;
import com.scalesampark.types.MessageType;

@Service
public class MessageServiceImpl extends AbstractSamparkService implements MessageService {

	@Autowired
	ParticipantJPARepository participantJPARepository;
	
	@Autowired
	MessageJPARepository messageJPARepository;
	@Value( "${secretKey}")
	private String secretKey;
	
	@Override
	public void saveMessage(String participant_uuid,MessageDto messageDto) throws InvalidDataException {
		Participant p=participantJPARepository.findById(participant_uuid).orElse(null);
		
			if(isValidParticipant(p)) {
				if(MessageType.SUPPORTEDTYPES.contains(messageDto.getMessage_type())){
					p.setLast_seen(LocalDateTime.now());
					Message messageEntity=MessageDto.of(messageDto);
					messageEntity.setMessage(CryptoGraphy.encryptMessage(messageEntity.getMessage(),secretKey));
					messageEntity.setMessage_uuid(UUID.randomUUID().toString());
					messageEntity.setParticipant(p);
					messageJPARepository.save(messageEntity);
				}
				else {
					throw new InvalidDataException("Invalid Message Type");
				}
				
			}
		
	}
	@Override
	public List<MessageDto> fetchPendingMessages(String participant_uuid){
		List<MessageDto> messages=null;
		Participant p=participantJPARepository.findById(participant_uuid).orElse(null);
		
		if(isValidParticipant(p)) {
			p.setLast_seen(LocalDateTime.now());
			participantJPARepository.save(p);
			List<Message> messageslist=messageJPARepository.findAll();
			messages=messageslist.stream().map(m->Message.toDTO(m)).collect(Collectors.toList());
	        messages= messages.stream().map(m->{m.setMessage(CryptoGraphy.decryptMessage(m.getMessage(), secretKey));return m;}).collect(Collectors.toList());
					
		}
		if(null==messages || messages.size()==0)
		{
			throw new MessageNotFoundException("No Pending Messages Found");
		}
		return messages;
	}
}
