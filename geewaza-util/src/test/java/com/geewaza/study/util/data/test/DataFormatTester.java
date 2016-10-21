package com.geewaza.study.util.data.test;

import com.geewaza.study.util.data.DataFormat;

/**
 * Created by WangHeng on 2016/10/12.
 */
public class DataFormatTester {

    public static void main(String[] args) {
        test03();
    }

    private static void test03() {
        long last = 25051470;
        double perSeconds = 100.0;
        System.out.println(DataFormat.lastTime(last, perSeconds));
    }

    private static void test02() {
        long time = 25051470;
        System.out.println(DataFormat.parseMillis(time));

    }

    private static void test01() {
        System.out.println(DataFormat.progressPrint(0, 82,100));
    }

}
