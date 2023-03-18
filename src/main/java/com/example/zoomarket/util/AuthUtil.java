package com.example.zoomarket.util;

public class AuthUtil {
    public static boolean checkPassword(String pass) {
        // Check length of password
        if (pass.length() < 8) {
            return false;
        }
        // Check if at least one letter and one number
        boolean hasLetter = false;
        boolean hasNum = false;
        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasNum = true;
            }
        }
        return hasLetter && hasNum;
    }
}
