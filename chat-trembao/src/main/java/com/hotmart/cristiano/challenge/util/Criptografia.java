package com.hotmart.cristiano.challenge.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

	public static String criptografarSenha(String senha) throws NoSuchAlgorithmException{		
		
		return getMD5Code(senha);
	}
	
	/**
	 * Returns the MD5
	 * @param text
	 * @return the MD5 code of text
	 * @throws NoSuchAlgorithmException
	 */
    public static String getMD5Code(String text) throws NoSuchAlgorithmException
    {
    	MessageDigest md = MessageDigest.getInstance("MD5");  
    	
    	try {
    		md.update(text.getBytes());
    	}catch (NullPointerException e){
    		return null;
    	}
    	BigInteger hash = new BigInteger(1, md.digest());  
 
    	return hash.toString(16);  
    }
	
}
 