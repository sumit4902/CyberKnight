package com.cyber.Algorithms;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

@Setter
@Getter
public class DES {

    private  final String ALGORITHM = "DES";

    // Encrypt plaintext with DES algorithm and given key
    public  String encrypt(String plaintext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt ciphertext with DES algorithm and given key
    public  String decrypt(String ciphertext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

   /* public static void main(String[] args) {
        try {
            String plaintext = "Hello, world!";
            String key = "12345678"; // 64-bit key (8 characters)
            System.out.println("Original: " + plaintext);

            String encrypted = encrypt(plaintext, key);
            System.out.println("Encrypted: " + encrypted);

            String decrypted = decrypt(encrypted, key);
            System.out.println("Decrypted: " + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } */
}