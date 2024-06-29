package com.zljin.config;

import java.util.Random;

public class BankCardNumberGenerator {
    private static final Random random = new Random();

    private BankCardNumberGenerator() {
    }

    public static String generateAccountNumber() {
        int length = 16;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }
}

