package com.geewaza.study.util.data.test;

import com.geewaza.study.util.data.DataFormat;

/**
 * Created by WangHeng on 2016/10/12.
 */
public class DataFormatTester {

    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        System.out.println(DataFormat.progressPrint(0, 82,100));
    }

}
