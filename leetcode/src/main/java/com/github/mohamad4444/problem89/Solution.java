package com.github.mohamad4444.problem89;

import java.util.List;

public class Solution {
    public void printb(int x){
        System.out.println("Binary representation of "+x+" is:"+Integer.toBinaryString(x) );
    }
    public List<Integer> grayCode(int n) {
        int x=-77;
        int y=0;
        // 00 1
        // 01 2
        // 11 1
        // 000 1 (takes 2)
        // 001 3  (1 left) 
        // 011 3  (1 left)
        // 111 1 (takes 2)

        // 0000 1 (takes 2)
        // 0001 4 (2 left) 2 alternating with the other 2
        // 0011 6 (4 left) 2 near each 3
        // 0111 4 (2 left) 2 alternating wit the other 2
        // 1111 1 (takes 2)
        //   1        1            1         1
        // 0010 0000 0001   0011  1110 1111 0111    0
      return null;
    }
}
