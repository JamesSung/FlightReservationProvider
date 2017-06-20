package com.james.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESCryptor
{
    private static final String ALGORITHM = "AES";
    private static final String KEY = "2Mbra537ajfDSJ56";//"1Hbfh667adfDEJ78";
    
    private static AESCryptor instance;
    
    private AESCryptor(){}
    
    public static AESCryptor getInstance(){
        if(instance == null){
            synchronized (AESCryptor.class) {
                if(instance == null){
                    instance = new AESCryptor();
                }
            }
        }
        return instance;
    }
    
    public String encrypt(String value) 
    {
        Cipher cipher;
        String result = null;
		try {
			cipher = Cipher.getInstance(AESCryptor.ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, generateKey());
			result = base64Encode(cipher.doFinal(value.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
               
    }
    
    public String decrypt(String value) 
    {
        String result = null;
		try {
	        Cipher cipher = Cipher.getInstance(AESCryptor.ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, generateKey());
	        result = new String(cipher.doFinal(base64Decode(value)),"UTF-8");           
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
    }
    
    private String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
    
    private byte[] base64Decode(String value) {
        return Base64.getDecoder().decode(value);
    }
    
    private Key generateKey() throws Exception 
    {
        Key key = new SecretKeySpec(AESCryptor.KEY.getBytes(),AESCryptor.ALGORITHM);
        return key;
    }
}