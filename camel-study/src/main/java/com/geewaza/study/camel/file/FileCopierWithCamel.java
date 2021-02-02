package com.geewaza.study.camel.file;

import com.geewaza.study.commons.util.ToolMethods;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p></p>
 *
 * @author : wanwei
 * @date : 2021-02-02 10:34
 **/
public class FileCopierWithCamel {

    public static void main(String[] args) throws Exception {
        final String resourceInPath = ToolMethods.getResourceFile("inbox").getAbsolutePath();
        final String resourceOutPath = ToolMethods.getResourceFile("outbox").getAbsolutePath();
        System.out.println("resourceInPath " + resourceInPath);
        System.out.println("resourceOutPath " + resourceOutPath);


        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("file:" + resourceInPath + "?noop=true").to("file:" + resourceOutPath);
            }
        });
        context.start();

        createNewFile();


        System.out.println("after context.start()");

        // 通用没有具体业务意义的代码，只是为了保证主线程不退出
        synchronized (FileCopierWithCamel.class) {
            FileCopierWithCamel.class.wait();
        }
    }

    private static void createNewFile() throws IOException {

        String fileName = ToolMethods.getResourceFile("inbox").getAbsolutePath() + "/" + UUID.randomUUID().toString() + ".txt";
        System.out.println("new file " + fileName);
        File file = ToolMethods.buildNewFile(fileName);
        System.out.println(file.getAbsolutePath());

    }
}
