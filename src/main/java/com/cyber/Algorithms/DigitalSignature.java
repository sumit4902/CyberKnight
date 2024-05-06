package com.cyber.Algorithms;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor                      
public class DigitalSignature {

    private KeyPair keyPair;

    // Constructor to generate key pair
   

    // Generate key pair for digital signature
    private KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Key size
        return keyPairGenerator.generateKeyPair();
    }

    // This Method takes input from the user and returns digital signature in string form
    public String outputSignature(String message) throws Exception {
    	 this.keyPair = generateKeyPair();
        // Get private key
        PrivateKey privateKey = keyPair.getPrivate();
        // Create digital signature
        byte[] digitalSignature = createDigitalSignature(message, privateKey);
        return bytesToHex(digitalSignature);
    }

    // This Method will take the digital signature and message from the user and return verification statement
    public String  outputResult(String message, String digitalSignature) throws Exception {
        // Get public key
        PublicKey publicKey = keyPair.getPublic();
        // Verify digital signature
        if( verifyDigitalSignature(message, hexToBytes(digitalSignature), publicKey))
        {
        	return "Verfied Successfully";
        }
        return "There is a Alteration in the Signature ";
    }

    // Create digital signature using private key
    private byte[] createDigitalSignature(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }

    // Verify digital signature using public key
    private boolean verifyDigitalSignature(String message, byte[] digitalSignature, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        return signature.verify(digitalSignature);
    }

    // Convert byte array to hexadecimal string (for display purposes)
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Convert hexadecimal string into byte array
    private byte[] hexToBytes(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                                 + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}

