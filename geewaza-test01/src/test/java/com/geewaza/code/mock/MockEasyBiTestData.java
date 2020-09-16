package com.geewaza.code.mock;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * <p>测试数据生成</p>
 *
 * @author : wangheng
 * @date : 2020-09-15 16:55
 **/

public class MockEasyBiTestData {

    static final String[] SICHUAN_CITIES = {"成都市", "绵阳市", "德阳市", "攀枝花市", "遂宁市", "南充市", "广元市", "乐山市", "宜宾市", "泸州市", "达州市", "广安市", "巴中市", "雅安市", "内江市", "自贡市", "资阳市", "眉山市"};

    static final Object[][] GENDERS = {{511, "男"},{489, "女"}};

    static final Object[][] BLOOD_TYPES = {{28, "A"},{24, "B"},{7, "AB"},{41, "O"}};

    static final Object[][] AGE_STAGE = {{118, "0"},{108, "10"},{158, "20"},{147, "30"},{168, "40"},{140, "50"},{100, "60"},{43, "70"},{16, "80"},{1, "90"}};


    public static String getValueFromStageArray(Object[][] stageArray) {
        List<String> rateArray = buildRateArray(stageArray);
        return getFromList(rateArray);
    }


    public static List<String> buildRateArray(Object[][] stageArray) {
        LinkedList<String> result = Lists.newLinkedList();
        for (Object[] item : stageArray) {
            for (int i = 0; i < (int)item[0]; i++) {
                result.add((String) item[1]);
            }
        }
        return result;
    }
    public static String getFromList(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }



    public static String getFromArray(String[] array, RandInt rand) {
        int index = rand.nextInt();
        return array[index == 0 ? 0 : array.length % index];
    }
    public static String getFromArray(String[] array) {
        Random random = new Random();
        return array[random.nextInt(array.length)];
    }


    @Test
    public void preTest() {
        final Random random = new Random();

        int count = 10000;
        for (int i = 0; i < count; i++) {
            long id = i + 1;
            String gender = getValueFromStageArray(GENDERS);
            String ageRange = getValueFromStageArray(AGE_STAGE);
            int age = Integer.parseInt(ageRange) + RandomUtils.nextInt(0, 10);
            String city = getFromArray(SICHUAN_CITIES);
            String bloodType = getValueFromStageArray(BLOOD_TYPES);
            int score = (int) normalDistribution(50, 200);
            System.out.println(String.format("%s,%s,%s,%s,%s,%s", id, gender, age, city, bloodType, score));
        }

    }

    //普通正态随机分布
//参数 u 均值
//参数 v 方差
    public static double normalDistribution(float u,float v){
        java.util.Random random = new java.util.Random();
        return Math.sqrt(v)*random.nextGaussian()+u;
    }

    public interface RandInt {
        public int nextInt();
    }
}
