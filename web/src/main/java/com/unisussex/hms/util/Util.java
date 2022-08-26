package com.unisussex.hms.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class Util {
    static SecureRandom r = new SecureRandom();

    public static void main(String[] args){
        System.out.println(hash("tosin"));
    }

    public static String hash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Change this to UTF-16 if needed
            md.update(text.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            String hex = String.format("%064x", new BigInteger(1, digest));
            return hex;
        } catch (Exception ex) {
            return text;
        }
    }
}
