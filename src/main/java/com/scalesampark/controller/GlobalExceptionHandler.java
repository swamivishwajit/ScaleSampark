package com.scalesampark.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scalesampark.exception.InvalidDataException;
import com.scalesampark.exception.InvalidMessageTypeException;
import com.scalesampark.exception.MessageNotFoundException;
import com.scalesampark.exception.ParticipentNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = ParticipentNotFoundException.class)
	public ResponseEntity<String> handleParticipentNotFoundException(ParticipentNotFoundException pe){
		return new ResponseEntity<String>(pe.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = MessageNotFoundException.class)
	public ResponseEntity<String> handleParticipentNotFoundException(MessageNotFoundException me){
		return new ResponseEntity<String>(me.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidMessageTypeException.class)
	public ResponseEntity<String> handleParticipentNotFoundException(InvalidMessageTypeException me){
		return new ResponseEntity<String>(me.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InvalidDataException.class)
	public ResponseEntity<String> handleAnyOtherException(InvalidDataException me){
		return new ResponseEntity<String>(me.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

}
