package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

/**
 * Created by WangHeng on 2016/3/29.
 * 求最短编辑距离
 */
public class EditDistance {

    @Test
    public void test01() {
        String[][] tests = {
                {"ALGORITHM","ALTRUISTIC"},
        };
        for (String[] test : tests) {
            System.out.println(editDistance(test[0], test[1]));
        }
    }


    public int editDistance(String x, String y) {

        int[][] c = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i <= x.length(); i++) {
            c[i][0] = i;
        }
        for (int j = 0; j <= y.length(); j++) {
            c[0][j] = j;
        }

        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                if (x.charAt(i-1) == y.charAt(j-1)) {
                    c[i][j] = c[i - 1][j - 1];
                } else {
                    c[i][j] = Math.min(Math.min(c[i-1][j],c[i-1][j-1]), c[i][j-1]) + 1;
                }
            }
        }
        return c[x.length()][y.length()];

    }
}
