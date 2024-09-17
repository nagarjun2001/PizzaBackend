//package com.pizza.utils;
//
//import java.security.SecureRandom;
//
//public class Password {
//
//    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
//    private static final int pwdlength = 8;
//
//    public static String generateRandomPassword() {
//        SecureRandom random = new SecureRandom();
//        StringBuilder sb = new StringBuilder(pwdlength);
//        for (int i = 0; i < pwdlength; i++) {
//            int index = random.nextInt(characters.length());
//            sb.append(characters.charAt(index));
//        }
//        return sb.toString();
//    }
//}
