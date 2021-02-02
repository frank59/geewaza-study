package com.geewaza.study.camel.http;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * <p></p>
 *
 * @author : wanwei
 * @date : 2021-02-02 11:05
 **/
public class HttpServer {


    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                // 在本代码段之下随后的说明中，会详细说明这个构造的含义
                from("jetty:http://0.0.0.0:8282/doHelloWorld")
                        .process(new HttpProcessor())
                        .to("log:helloworld?showExchangeId=true");
            }
        });
        context.start();
    }
}
