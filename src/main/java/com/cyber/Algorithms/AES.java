package com.cyber.Algorithms;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {

    public  final String ALGORITHM = "AES";
    public  final String MODE = "ECB"; // ECB mode doesn't require an IV
    public  final String PADDING = "PKCS5Padding"; // Padding scheme

    // Encrypt plaintext with AES algorithm and given key
     public String encrypt(String plaintext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + MODE + "/" + PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt ciphertext with AES algorithm and given key
     public String decrypt(String ciphertext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + MODE + "/" + PADDING);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

//    public static void main(String[] args) {
//        try {
//            String plaintext = "Hello, world!";
//            String key = "0123456789abcdef"; // 128-bit key
//            System.out.println("Original: " + plaintext);
//
//            String encrypted = encrypt(plaintext, key);
//            System.out.println("Encrypted: " + encrypted);
//
//            String decrypted = decrypt(encrypted, key);
//            System.out.println("Decrypted: " + decrypted);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
