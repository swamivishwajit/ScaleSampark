package com.scalesampark.dto;

import com.scalesampark.dto.domain.Message;
import com.scalesampark.exception.InvalidDataException;
import com.scalesampark.types.MessageType;

public class MessageDto {
	
	public MessageDto(String participant_uuid,String message_uuid, Number message_type, String message) {
		super();
		this.message_type = message_type;
		this.message = message;
		this.message_uuid=message_uuid;
		this.participant_uuid=participant_uuid;
	}
	private String participant_uuid;
	public String getParticipant_uuid() {
		return participant_uuid;
	}
	public void setParticipant_uuid(String participant_uuid) {
		this.participant_uuid = participant_uuid;
	}
	private String message_uuid;
	public String getMessage_uuid() {
		return message_uuid;
	}
	public void setMessage_uuid(String message_uuid) {
		this.message_uuid = message_uuid;
	}
	private Number message_type;
	private String message;
	public Number getMessage_type() {
		return message_type;
	}
	public void setMessage_type(Number message_type) {
		this.message_type = message_type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static Message of(MessageDto dto) {
		if(null!=dto &&(null!=dto.getMessage() && null !=dto.getMessage_type()))
		{
		Message message=new Message();
		message.setMessage(dto.getMessage());
		message.setMessage_type(MessageType.get(dto.getMessage_type().toString()));
		return message;
		}
		else {
			throw new InvalidDataException("Empty message details");
		}
	}

}
