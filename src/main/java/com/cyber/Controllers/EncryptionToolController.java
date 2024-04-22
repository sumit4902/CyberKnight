package com.cyber.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.Payload.EncryptionToolDto;
import com.cyber.Services.EncryptionToolService;



@RestController()
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class EncryptionToolController {

	
	   @Autowired
       private EncryptionToolService entool;
	   
	 // method for encryption 
	  @PostMapping("/encrypt/Message")
	 ResponseEntity<EncryptionToolDto> encryptMessage(@RequestBody EncryptionToolDto endto,
			    
			 @RequestParam(name = "key",defaultValue ="",required = true) String key,
			 @RequestParam(name = "privateKey",defaultValue ="",required = true) String privateKey,
			 @RequestParam(name = "publicKey",defaultValue ="",required = true) String publicKey,
	         @RequestParam(name = "algoType",defaultValue ="DES",required = true) String algoType)
	 
	 {
		
		 EncryptionToolDto cipherText = this.entool.Encryption(endto.getPlainText(), key,publicKey,privateKey,algoType);
		 return new ResponseEntity<EncryptionToolDto>(cipherText,HttpStatus.OK);
	 }
	  
	  // method for Decryption 
	  
	  @PostMapping("/decrypt/Message")
	  ResponseEntity<EncryptionToolDto> decryptMessage(@RequestBody  EncryptionToolDto endto,
			     @RequestParam(name = "key",defaultValue ="",required = true) String key,
				 @RequestParam(name = "privateKey",defaultValue ="",required = true) String privateKey,
				 @RequestParam(name = "publicKey",defaultValue ="",required = true) String publicKey,
	             @RequestParam(name = "algoType",defaultValue ="DES",required = true) String algoType)
		 {
			 EncryptionToolDto plainText = this.entool.Decryption(endto.getCipherText(),key, publicKey, privateKey,algoType);
			 return new ResponseEntity<EncryptionToolDto>(plainText,HttpStatus.OK);
		 }
	  
	
}
