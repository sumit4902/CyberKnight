package com.cyber.Algorithms;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class DigitalSignature {

	{/*  public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPair keyPair = generateKeyPair();

        // Get private and public keys
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Original message
        String message = "Hello, world!";

        // Create digital signature
        byte[] digitalSignature = createDigitalSignature(message, privateKey);

        // Verify digital signature
        boolean isVerified = verifyDigitalSignature(message, digitalSignature, publicKey);

        System.out.println("Original message: " + message);
        System.out.println("Digital signature: " + bytesToHex(digitalSignature));
        System.out.println("Is verified? " + isVerified);
    }   */}

    // Generate key pair for digital signature
    public  KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Key size
        return keyPairGenerator.generateKeyPair();
    }

    // Create digital signature using private key
    public  byte[] createDigitalSignature(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }

    // Verify digital signature using public key
    public  boolean verifyDigitalSignature(String message, byte[] digitalSignature, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        return signature.verify(digitalSignature);
    }

    // Convert byte array to hexadecimal string (for display purposes)
    public  String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
