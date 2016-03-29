package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

/**
 * Created by WangHeng on 2016/3/29.
 */
public class LCS {

    @Test
    public void test01() {
        String[][] tests = {
                {"ABCBDAB","BDCABA"},
        };
        for (String[] test : tests) {
            System.out.println(LCS(test[0], test[1], null, null));
        }
    }

    enum Direct {
        Left, Top, LeftTop;
    }

    private int LCS(String x, String y, int[][] c, Direct[][] b) {
        if (c == null) {
            c = new int[x.length()+1][y.length()+1];
        }
        if (b == null) {
            b = new Direct[x.length()+1][y.length()+1];
        }
        if (x == null || y == null || x.length() == 0|| y.length() == 0) {
            return 0;
        }

        for(int i = 0; i <= x.length(); i++){
            c[i][0] = 0;
        }
        for(int j = 0; j <= y.length(); j++){
            c[0][j] = 0;
        }
        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = Direct.LeftTop;
                } else {
                    c[i][j] =  Math.max(c[i - 1][j], c[i][j - 1]);
                    if ( c[i][j] == c[i - 1][j]) {
                        b[i][j] = Direct.Top;
                    } else {
                        b[i][j] = Direct.Left;
                    }
                }
            }
        }
        return c[x.length()][y.length()];
    }
}
