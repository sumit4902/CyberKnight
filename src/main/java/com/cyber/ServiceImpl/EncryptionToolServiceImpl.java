package com.cyber.ServiceImpl;

import org.springframework.stereotype.Service;

import com.cyber.Algorithms.AES;
import com.cyber.Algorithms.DES;
import com.cyber.Payload.EncryptionToolDto;
import com.cyber.Services.EncryptionToolService;

@Service
public class EncryptionToolServiceImpl implements EncryptionToolService{

	private DES desalgo = new DES();
	private AES aesalgo = new AES();
	EncryptionToolDto endto= new EncryptionToolDto();
	
	
	
	@Override
	public EncryptionToolDto Encryption(String message, String key, String publicKey, String privateKey,String algoType) {
		
		
		String cipherText=null;
		
		if(algoType.equalsIgnoreCase("DES"))
		{
			if(key!="" && message!="")
			{
				  key = "12345678"+key;
				  key= key.substring(key.length()-8,key.length());
				try {
					
					 cipherText= desalgo.encrypt(message,key);
					
				}
				catch(Exception e)
				{
					System.out.print(e);
				}
				endto.setPlainText(message);
			    endto.setCipherText(cipherText);
			    return endto;
			}
			
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////
		// for AES
		if(algoType.equalsIgnoreCase("AES"))
		{
			if(key!="" && message!="")
			{
				  key = "0123456789abcdef"+key;
				  key= key.substring(key.length()-16,key.length());
				try {
					
					 cipherText= aesalgo.encrypt(message, key);
					
				}
				catch(Exception e)
				{
					System.out.print(e);
				}
				endto.setPlainText(message);
			    endto.setCipherText(cipherText);
			    return endto;
			}
			
		}
		
		
		return null;
	}

	@Override
	public EncryptionToolDto Decryption(String message, String key, String publicKey, String privateKey,String algoType) {
		String plainText =null;
		if(algoType.equalsIgnoreCase("DES"))
		{
			if(key!="" && message!="")
			{
				  key = "12345678"+key;
				  key= key.substring(key.length()-8,key.length());
				  
				try {
					
					 plainText = desalgo.decrypt(message,key);
					
					
				}
				catch(Exception e)
				{
					System.out.print(e);
				}
				endto.setPlainText(plainText);
			    endto.setCipherText(message);
			    return endto;
			}
			
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// for AES
		if(algoType.equalsIgnoreCase("AES"))
		{
			if(key!="" && message!="")
			{
				  key = "0123456789abcdef"+key;
				  key= key.substring(key.length()-16,key.length());
				  System.out.println(message+" Key Is: " +key);
				try {
					
					 plainText = aesalgo.decrypt(message,key);
					
					
				}
				catch(Exception e)
				{
					System.out.print(e);
				}
				endto.setPlainText(plainText);
			    endto.setCipherText(message);
			    return endto;
			}
			
		}
		
		return null;
	}

}
