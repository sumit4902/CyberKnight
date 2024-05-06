package com.cyber.ServiceImpl;

import org.springframework.stereotype.Service;

import com.cyber.Algorithms.AES;
import com.cyber.Algorithms.DES;
import com.cyber.Algorithms.DigitalSignature;
import com.cyber.Algorithms.MAC;
import com.cyber.Algorithms.MonoAlphabetic;
import com.cyber.Algorithms.PolyalphabeticVigenereCipher;
import com.cyber.Algorithms.ShiftCipher;
import com.cyber.Payload.EncryptionToolDto;
import com.cyber.Services.EncryptionToolService;

@Service
public class EncryptionToolServiceImpl implements EncryptionToolService{

	private DES desalgo = new DES();
	private AES aesalgo = new AES();
	private DigitalSignature digitalSignature = new DigitalSignature();
	private ShiftCipher shiftCipher = new ShiftCipher();
	private PolyalphabeticVigenereCipher polyalphabetic = new PolyalphabeticVigenereCipher();
	private MonoAlphabetic monoalphabetic = new MonoAlphabetic();
	private MAC mac = new MAC();
	
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
		
		/////////////////////////////////// DIGITAL SIGNATURE ////////////////////////////////////////////////
		if(algoType.equalsIgnoreCase("DigitalSignature"))
		{
			if( message!="")
			{
				 
				try {
					
					 cipherText= digitalSignature.outputSignature(message);
					
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
		
		////////////////////////////////////////// ShiftCipher //////////////////////////////////////////////////
		if(algoType.equalsIgnoreCase("ShiftCipher"))
		{
			if(key!="" && message!="")   // Key Should be integer it indicates the No of Shift //
			{
				 
				try {
					
					 cipherText= shiftCipher.encrypt(message,Integer.parseInt(key));
					
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
		
         
		
		
         ////////////////////////////////////////////////////// PolyAlphabetic /////////////////////////////
                if(algoType.equalsIgnoreCase("polyalphabetic"))
                {
               if(key!="" && message!="")  
                {

                try {

                cipherText= polyalphabetic.encrypt(message,key);
                      
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
                
                
                
    ////////////////////////////////////////////////   MonoAlphabetic //////////////////////////////////////////
                if(algoType.equalsIgnoreCase("Monoalphabetic"))
                {
                	 key = "asdfghjklzxcvbnmqwertyuiop"+key;
   				  key= key.substring(key.length()-26,key.length());
               if(key!="" && message!="")   // Key Should be of length 26 char // 
                {

                try {

                cipherText= monoalphabetic.encrypt(message,key);

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
                
///////////////////////////////////////////////////////// Message Authentication Code /////////////////////////////////////////////////
                
                
                
                if(algoType.equalsIgnoreCase("MAC"))
                { 
                	
               if( message!="")   
                {

                try {

                cipherText= mac.generateMAC(message);

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

//	
//	
//	
//	             |\
//	             | \
//	             | /
//	             |/   
//	
	////////  Decryption Agorithms /////////////
	
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
		
		////////////////////////////////////////Digital Signature //////////////////////////////////////
		if(algoType.equalsIgnoreCase("DigitalSignature"))
		{
			if(key!="" && message!="")
			{
				 
				try {
					
					 plainText = digitalSignature.outputResult(message,key);
					
					
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
		
		///////////////////////////////////////// ShiftCipher ///////////////////////////////////////////////
		if(algoType.equalsIgnoreCase("ShiftCipher"))
		{
			if(key!="" && message!="")
			{
				 
				try {
					
					 plainText = shiftCipher.decrypt(message, Integer.parseInt(key));
					
					
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
		
///////////////////////////////////////// PolyAlphabetic  ///////////////////////////////////////////////
if(algoType.equalsIgnoreCase("Ployalphabetic"))
{
if(key!="" && message!="")
{

try {

plainText = polyalphabetic.decrypt(message, key);


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
	         
///////////////////////////////////////// MonoAlphabetic ///////////////////////////////////////////////
if(algoType.equalsIgnoreCase("Monoalphabetic"))
{
if(key!="" && message!="")
{
	 key = "asdfghjklzxcvbnmqwertyuiop"+key;
     key= key.substring(key.length()-26,key.length());
try {

plainText = monoalphabetic.decrypt(message,key);


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



   /////////////////////////////////////////////////////////////////// MAC ////////////////////////
if(algoType.equalsIgnoreCase("MAC"))
{
if(key!="" && message!="")
{
	try {
          
		
  plainText = mac.verifyMAC(message,key);


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
