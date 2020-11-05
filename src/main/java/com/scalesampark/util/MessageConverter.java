package com.scalesampark.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.scalesampark.types.MessageType;

@Converter(autoApply = true)
class MessageConverter implements AttributeConverter<MessageType, String> {
 
   @Override
   public String convertToDatabaseColumn(MessageType messageType) {
       if (messageType == null) {
           return null;
       }
       return messageType.getMessageTypeCode();
   }
 
   @Override
   public MessageType convertToEntityAttribute(String value) {
       if (value == null) {
           return null;
       }
       try {
           return MessageType.valueOf(value.toString());
       } catch (IllegalArgumentException e) {
           return null;
       }
   }
 
}
