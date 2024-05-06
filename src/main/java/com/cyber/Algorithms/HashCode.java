package com.cyber.Algorithms;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCode {

    // Method to generate the SHA-256 hash of a string
    public static String sha256Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String plaintext = "Hello, world!";
        try {
            String hash = sha256Hash(plaintext);
            System.out.println("Plaintext: " + plaintext);
            System.out.println("SHA-256 hash: " + hash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found!");
        }
    }
}
