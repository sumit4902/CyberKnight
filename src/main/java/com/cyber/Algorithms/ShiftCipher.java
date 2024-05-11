package com.cyber.Algorithms;

public class ShiftCipher {
    
    // Encrypts text using a shift specified by key
    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char shifted = (char) (((ch - 'A' + (key)) % 26) + 'A');
                result.append(shifted);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    
    // Decrypts text using a shift specified by key
    public static String decrypt(String text, int key) {
        // Decrypting is the same as encrypting with negative key
        return encrypt(text, (-key));
    }

  
}
