package com.spring.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordEncoderSha512 implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return hashWithSha512(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = encode(rawPassword);
        return hashedPassword.equals(encodedPassword);
    }

    private String hashWithSha512(String input) {
        StringBuilder result = new StringBuilder();
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Bad algorithm");
        }
        byte[] digested = messageDigest.digest(input.getBytes());
        for (byte b : digested) {
            result.append(Integer.toHexString(0xFF & b));
        }
        return result.toString();
    }
}
