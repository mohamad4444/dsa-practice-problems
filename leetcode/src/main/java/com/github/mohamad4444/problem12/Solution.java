package com.github.mohamad4444.problem12;

public class Solution {
    public String intToRoman(int num) {
        StringBuilder ans=new StringBuilder();
        char[] roman=new char[]{'I','V','X','L','C','D','M'};
        int zeros=0;
        while(num!=0){
            int digit=num%10;
            num=num/10;
            //5 Cases 1-3,4,5,6-8,9
            if(digit>=1 && digit<=3){
                while(digit>0){
                    ans.append(roman[zeros*2]);
                    digit--;
                }
            }else if(digit==4){ 
                ans.append(roman[zeros*2+1]);
                ans.append(roman[zeros*2]);
            }else if(digit==5){
                ans.append(roman[zeros*2+1]);
            }else if(digit>=6 && digit<=8){
                while(digit>5){
                    ans.append(roman[zeros*2]);
                    digit--;
                }
                ans.append(roman[zeros*2+1]);
            }else if(digit==9){
                ans.append(roman[zeros*2+2]);
                ans.append(roman[zeros*2]);
            }
            zeros++;

        }
        return ans.reverse().toString();
    }
}
