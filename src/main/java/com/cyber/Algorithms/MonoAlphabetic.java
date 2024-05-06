package com.cyber.Algorithms;
import java.util.HashMap;
import java.util.Map;

public class MonoAlphabetic {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Map<Character, Character> encryptionKey = new HashMap<>();
    private Map<Character, Character> decryptionKey = new HashMap<>();

   
    public void SetKey(String key)
    {
    	if (key.length() != ALPHABET.length()) {
            throw new IllegalArgumentException("Key length must be equal to 26 (the length of the alphabet)");
        }
        for (int i = 0; i < ALPHABET.length(); i++) {
            char plain = ALPHABET.charAt(i);
            char cipher = Character.toUpperCase(key.charAt(i));
            encryptionKey.put(plain, cipher);
            decryptionKey.put(cipher, plain);
        }
    }

    public String encrypt(String plaintext , String key) {
    	
    	
    	SetKey(key);
    	
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                ciphertext.append(encryptionKey.getOrDefault(c, c));
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext  , String key) {
    	
    	SetKey(key);
    	
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                plaintext.append(decryptionKey.getOrDefault(c, c));
            } else {
                plaintext.append(c);
            }
        }
        return plaintext.toString();
    }

   
}
