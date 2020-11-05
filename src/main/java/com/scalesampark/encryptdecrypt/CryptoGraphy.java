package com.scalesampark.encryptdecrypt;

public class CryptoGraphy {
	
	
	public static String encryptMessage(String message,String secretKey) {
		return AES.encrypt(message, secretKey) ;
	}

	public static String decryptMessage(String encryptedMessage,String secretKey) {
	    return  AES.decrypt(encryptedMessage, secretKey) ;
	}
}
