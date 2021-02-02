package com.geewaza.code.test01.camel;

import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author : wanwei
 * @date : 2020-12-18 11:22
 **/
public class Sleep {

    public static void second(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
