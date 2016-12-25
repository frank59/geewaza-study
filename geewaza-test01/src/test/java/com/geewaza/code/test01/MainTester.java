package com.geewaza.code.test01;

import com.geewaza.code.test01.utill.ThreadLoggerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangh on 2016/12/25.
 */
public class MainTester {

    public static void main(String[] args) {
        test02();
    }

    private static void test02() {
        for (int i = 1; i < 4; i++) {
            final String loggerName = "logger" + i;
            ThreadLoggerHelper.IndependentLoggerRunner runner = new ThreadLoggerHelper.IndependentLoggerRunner() {
                @Override
                protected void runJob() {
                    ThreadLoggerHelper.get().info("this is {}", loggerName);
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            runner.setLoggerName(loggerName);
            System.out.println("开始执行" + loggerName);
            new Thread(runner).start();
        }
    }

    private static void test01() {
        Logger logger = LoggerFactory.getLogger(MainTester.class);
        logger.info("This is {} logger", "SLF4J");
    }
}
