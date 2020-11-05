package com.scalesampark.dto.domain;

import static javax.persistence.EnumType.ORDINAL;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.scalesampark.dto.MessageDto;
import com.scalesampark.types.MessageType;

@Entity
@Table(name = "MESSAGE")
public class Message {
	
	@Id
	@Column
	private String message_uuid;
	
	public Message() {
		
	}
	public Message(String message_uuid, MessageType message_type, String message, Participant participant) {
		super();
		this.message_uuid = message_uuid;
		this.message_type = message_type;
		this.message = message;
		this.participant = participant;
	}

	@Column(nullable = false)
	@Enumerated(ORDINAL)
	private MessageType message_type;
	
	public String getMessage_uuid() {
		return message_uuid;
	}

	public void setMessage_uuid(String message_uuid) {
		this.message_uuid = message_uuid;
	}

	public MessageType getMessage_type() {
		return message_type;
	}

	public void setMessage_type(MessageType message_type) {
		this.message_type = message_type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	@Column
	private String message;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_uuid", referencedColumnName = "participant_uuid")
	private Participant participant;
	
	public static MessageDto toDTO(Message message) {
		return new MessageDto(message.getParticipant().getParticipant_uuid(),message.getMessage_uuid(),Integer.parseInt(message.getMessage_type().getMessageTypeCode()),message.getMessage());
	}

}
