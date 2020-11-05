package com.scalesampark.types;

import java.util.Arrays;
import java.util.List;

import com.scalesampark.exception.InvalidMessageTypeException;

public enum MessageType {
	
	INVALID("0"),TEXT("1"), MULTIMEDIA("2");

    private String typeCode;
    public static List<Number> SUPPORTEDTYPES=Arrays.asList(1);

    public static MessageType get(final String typeCode) {
    	if("0".equals(typeCode)) {
    		throw new InvalidMessageTypeException("Invalid Message Type");
    	}
    	for(MessageType type:values()) {
    		if(typeCode.equals(type.getMessageTypeCode())) {
    			return type;
    		}
    	}
    		throw new InvalidMessageTypeException("Invalid Message Type");
    }
    private MessageType(final String typeCode) {
    	this.typeCode=typeCode;
    }
    public String getMessageTypeCode() {
    	return this.typeCode;
    }
    
}
