package com.cyber.Services;

import com.cyber.Payload.EncryptionToolDto;

public interface EncryptionToolService {
	
	EncryptionToolDto Encryption(String message ,String key,String publicKey,String privateKey,String algoType);
	
	EncryptionToolDto Decryption(String message ,String key,String publicKey,String privateKey,String algoType);

}
