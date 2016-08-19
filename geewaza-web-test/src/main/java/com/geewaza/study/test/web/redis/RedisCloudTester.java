package com.geewaza.study.test.web.redis;

import com.geewaza.study.commons.jedis.RedisManager;
import com.geewaza.study.commons.jedis.SentinelRedisManager;
import com.geewaza.study.commons.jedis.ShardedRedisManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by WangHeng on 2016/8/19.
 */
public class RedisCloudTester {


    public static void main(String[] args) throws Exception {
        test01();
    }

    private static void test02() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        ShardedRedisManager redisManager = context.getBean("shardedRedisManager", ShardedRedisManager.class);
//        System.out.println(redisManager.getServers());

        System.out.println(redisManager.get("age"));
    }

    private static void test01() throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        SentinelRedisManager redisManager = context.getBean("sentinelRedisManager", SentinelRedisManager.class);
//        System.out.println(redisManager.getServers());

        System.out.println(redisManager.get("age"));
    }

}
