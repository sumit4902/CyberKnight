package com.cyber.Algorithms;

public class PolyalphabeticVigenereCipher {

    // Encrypts plaintext using the Vigenère cipher with the provided key
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase(); // Convert plaintext to uppercase
        int keyIndex = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            if (Character.isLetter(plainChar)) {
                char keyChar = key.charAt(keyIndex % key.length());
                int shift = keyChar - 'A';
                char encryptedChar = (char) (((plainChar - 'A' + shift ) % 26) + 'A');

                ciphertext.append(encryptedChar);
                keyIndex++;
            } else {
                // If the character is not a letter, leave it unchanged
                ciphertext.append(plainChar);
            }
        }
        return ciphertext.toString();
    }

    // Decrypts ciphertext encrypted with the Vigenère cipher using the provided key
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase(); // Convert ciphertext to uppercase
        int keyIndex = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            if (Character.isLetter(cipherChar)) {
                char keyChar = key.charAt(keyIndex % key.length());
                int shift = keyChar - 'A';
                char decryptedChar = (char) (((cipherChar - 'A' - (shift ) + 26) % 26) + 'A');

                plaintext.append(decryptedChar);
                keyIndex++;
            } else {
                // If the character is not a letter, leave it unchanged
                plaintext.append(cipherChar);
            }
        }
        return plaintext.toString();
    }

    
}
