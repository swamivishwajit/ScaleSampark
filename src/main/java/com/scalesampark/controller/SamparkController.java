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

import com.scalesampark.dto.ParticipantDto;
import com.scalesampark.dto.ParticipantResponseDto;
import com.scalesampark.exception.InvalidDataException;
import com.scalesampark.exception.ParticipentNotFoundException;
import com.scalesampark.service.SamparkService;

@RestController
@RequestMapping(value = "/sampark")
public class SamparkController {
	
	@Autowired
	SamparkService samparkService;
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResponseEntity<String> registerParticipant(@RequestBody ParticipantDto participentDto) throws InvalidDataException{
		
			String response=samparkService.regieterParticipant(participentDto);
			return new ResponseEntity<>(response, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/participents/{participant_uuid}")
	public ResponseEntity<List<ParticipantResponseDto>> getRegisteredParticepents(@PathVariable("participant_uuid")  String participant_uuid) throws ParticipentNotFoundException{
		
		return new ResponseEntity<>(samparkService.getAllParticipants(participant_uuid), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value ="{participant_uuid}/deregister",method = RequestMethod.DELETE)
	public ResponseEntity deRegisterParticipant(@PathVariable("participant_uuid")  String participant_uuid){
		samparkService.deRegisterParticipant(participant_uuid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
