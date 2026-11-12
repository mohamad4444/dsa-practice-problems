package com.github.mohamad4444.problem168;

import java.util.*;

public class Solution {
    public String convertToTitle(int columnNumber) {
        String res = "";
        // +64
        while (columnNumber != 0) {

            int charnum = (columnNumber % 26 + 64);
            if (columnNumber % 26 == 0) {
                charnum = 90;
                char last = (char) charnum; // right character
                res = last + res;
                columnNumber = columnNumber / 26 - 1;
                continue;
            }
            char last = (char) charnum; // right character
            res = last + res;
            columnNumber = columnNumber / 26;

        }
        return res;

    }
}
