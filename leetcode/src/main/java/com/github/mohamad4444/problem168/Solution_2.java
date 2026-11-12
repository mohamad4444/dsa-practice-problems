package com.github.mohamad4444.problem168;

import java.util.*;

public class Solution_2 {
    public String convertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        
        while (columnNumber > 0) {
            columnNumber--; 
            // Insert the character directly at the beginning
            res.insert(0, (char) ('A' + (columnNumber % 26)));
            columnNumber /= 26;
        }
        
        return res.toString();
    }
}
