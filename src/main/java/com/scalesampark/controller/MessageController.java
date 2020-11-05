package com.scalesampark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scalesampark.dto.MessageDto;
import com.scalesampark.exception.InvalidDataException;
import com.scalesampark.service.MessageService;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{participant_uuid}/save",method = RequestMethod.POST)
	public ResponseEntity RegisterMessage(@PathVariable("participant_uuid") String participant_uuid,@RequestBody MessageDto messageDto) throws InvalidDataException
	{
		messageService.saveMessage(participant_uuid,messageDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value = "{participant_uuid}/fetchall",method = RequestMethod.GET)
	public ResponseEntity<List<MessageDto>> fetchPendingMessages(@PathVariable("participant_uuid") String participant_uuid){
	
		return new ResponseEntity<>(messageService.fetchPendingMessages(participant_uuid), HttpStatus.OK);
	}
}
