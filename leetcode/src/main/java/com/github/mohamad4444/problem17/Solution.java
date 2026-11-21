package com.github.mohamad4444.problem17;

import java.util.List;

public class Solution {
    public List<String> letterCombinations(String digits) {
    char[][] letters = {
        {'a', 'b', 'c'}, //0=2
        {'d', 'e', 'f'}, //1=3
        {'g', 'h', 'i'}, //2=4
        {'j', 'k', 'l'}, // 3=5
        {'m', 'n', 'o'}, //4=6
        {'p', 'q', 'r', 's'},//5=7
        {'t', 'u', 'v'},//6=8
        {'w', 'x', 'y', 'z'} //7=9
    };
    int total=1;

    for(char digit:digits.toCharArray()){
        total*= letters[digit-50].length;
    }
    
    

    return null;
    }
}
