package com.academy;

import jakarta.xml.bind.DatatypeConverter;

import java.security.SecureRandom;

public class CreateKey {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32]; // 32 字节 = 256 位
        random.nextBytes(bytes);

        String secretKey = DatatypeConverter.printHexBinary(bytes);
        System.out.println(secretKey);
    }
}
