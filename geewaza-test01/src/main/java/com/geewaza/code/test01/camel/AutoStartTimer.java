package com.geewaza.code.test01.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * <p></p>
 *
 * @author : wanwei
 * @date : 2020-12-18 11:18
 **/
public class AutoStartTimer {


    public static void main(String[] args) throws Exception {
        init();

        Sleep.second(10000);

    }

    /**
     * 启动一个每两秒执行一次的定时任务，
     * 该定时任务每次执行事后将当前时间输出到异常和错误反馈的输出流中
     * @throws Exception
     */
    public static void init() throws Exception {
        DefaultCamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("timer://myTimer?period=2000")
                        .setBody(simple("Current time is [ ${header.firedTime} ]"))
                        .to("stream:err");
            }
        });
        camelContext.start();
    }

}
