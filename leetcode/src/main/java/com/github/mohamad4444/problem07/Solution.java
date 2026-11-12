package com.github.mohamad4444.problem07;

public class Solution {
    public int reverse(int x) {
        int result = 0;
        int count = 0;
        boolean positive = x > 0;
        int[] max = new int[]{2, 1, 4, 7, 4, 8, 3, 6, 4, 7};
        int[] min = new int[]{-2, -1, -4, -7, -4, -8, -3, -6, -4, -8}; 
        boolean oneDigitSmaller = false;
        boolean possibleOverflow = false;
        while (x != 0) {
            int lastDigit = x % 10;
            if (positive && max[count] > lastDigit) {
                oneDigitSmaller = true;
            } else if (positive && max[count] < lastDigit && !oneDigitSmaller) {
                possibleOverflow = true;
            } else if (!positive && min[count] < lastDigit) {
                oneDigitSmaller = true;
            } else if (!positive && min[count] > lastDigit && !oneDigitSmaller) {
                possibleOverflow = true;
            }
            count++;
            if (count == 10 && possibleOverflow) {
                return 0;
            }
            result = result + lastDigit;
            x = x / 10;
            
            if (x != 0) {
                result = result * 10;
            }
        }
        return result;
    }
}
