package com.cyber.Algorithms;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MAC {

//    public static void main(String[] args) {
//        try {
//            // Message to be authenticated
//            String message = "Hello, world!";
//            // Secret key for HMAC
//            String secretKey = "secretKey";
//
//            // Generate MAC
//            String mac = generateMAC(message, secretKey);
//            System.out.println("Generated MAC: " + mac);
//
//            // Verify MAC
//            boolean isValid = verifyMAC(message, mac, secretKey);
//            System.out.println("Is MAC valid? " + isValid);
//        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//            e.printStackTrace();
//        }
//    }

    // Method to generate MAC
	String secretKey="secretkeysdkfjlkjdkkdkjlkjsskks";
    public  String generateMAC(String message)
            throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        hmac.init(secretKeySpec);
        byte[] macBytes = hmac.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(macBytes);
    }

    // Method to verify MAC
    public  String  verifyMAC(String message, String mac)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String generatedMac = generateMAC(message);
       if( mac.equals(generatedMac))
       {
    	   return "Message is Verified Successfully...";
    	   
       }
       else {
    	   return "There is a Alteration in the message";
       }
    }
}
