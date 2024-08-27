package org.example.secvg;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptHashing {
    protected String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    protected boolean isHashCorrect(String inputPassword, String storedHash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(inputPassword, storedHash);
    }
}