package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by WangHeng on 2016/3/22.
 * 6. ZigZag Conversion
 */
public class ZigZagConversion {

    @Test
    public void test01() {
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
        assertEquals("agmbfhlnceikdj", convert("abcdefghijklmn", 4));
        assertEquals("abcd", convert("abcd", 4));
        assertEquals("abcd", convert("abcd", 1));
        assertEquals("", convert("", 1));
    }

    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        if (s.length() <= numRows) {
            return s;
        }
        int step = 2*(numRows - 1);
        char[] src = s.toCharArray();
        char[] result = new char[s.length()];
        int k =0;
        int times = s.length() / step + (s.length() % step == 0? 0:1);
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                for (int j = 0; j< times; j++) {
                    result[k++] = src[i + (j*step)];
                }
            } else if (i == numRows - 1) {
                for (int j = 0; j< times; j++) {
                    if (i + (j*step) < s.length()){
                        result[k++] = src[i + (j*step)];
                    }
                }
            } else {
                for (int j = 0; j< times; j++) {
                   if (i + (j*step) < s.length()) {
                       result[k++] = src[i + (j*step)];
                   }
                   if (step*(j + 1) - i < s.length()){
                        result[k++] = src[step*(j + 1) - i];
                    }
                }
            }
        }
        return new String(result);
    }
}
