package com.scalesampark.service;

import java.util.List;

import com.scalesampark.dto.MessageDto;
import com.scalesampark.exception.InvalidDataException;

public interface MessageService {
	public void saveMessage(String participant_uuid,MessageDto messageDto) throws InvalidDataException;
	public List<MessageDto> fetchPendingMessages(String participant_uuid);

}
