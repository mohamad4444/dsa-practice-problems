package com.github.mohamad4444.problem08;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("==================================================");
        System.out.println("Running Problem 08: myAtoi");
        System.out.println("==================================================");

        String[] testCases = {
            "-2147483649",      // Standard positive number / overflow
            "   -42",           // Leading whitespace and negative sign
            "4193 with words",  // Number followed by text
            "words and 987",    // Text followed by number
            "-91283472332",     // Out of bounds (underflow)
            "+1",               // Explicit positive sign
            "00000-42a1234",    // Leading zeros
            "3.14159",          // Decimal characters
            "   ",              // Whitespace only
            ""                  // Empty string
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ": \"" + testCases[i] + "\"");
            try {
                int result = sol.myAtoi(testCases[i]);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Result: Threw " + e.getClass().getSimpleName());
            }
            System.out.println("----------------------------------------");
        }
    }
}
