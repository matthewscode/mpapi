package com.mp.ttapi.security;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;


@Service("tokenService")
public class TokenService {
	
	private static final String key = "mpm12345mpm12345";
	private static final String initVector = "RandomInitVector";

	public String encrypt() throws Exception{
		 try {
			 	String value = ((Long)(System.currentTimeMillis() / 1000)).toString();
	            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

	            byte[] encrypted = cipher.doFinal(value.getBytes());

	            return "{\"token\" : \"" + Base64.encodeBase64URLSafeString(encrypted) + "\"}";
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return null;
	}
	public String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
	public boolean isTokenValid(String token) {
		Long sysTime = (Long)(System.currentTimeMillis() / 1000);
		Long tokenTime = Long.parseLong(decrypt(token));
		if(((sysTime - tokenTime) <= 5000) && ((sysTime - tokenTime) >= 0)){
			return true;
		}
		return false;
	}
	
}
