package com.geewaza.study.camel.http;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.http.common.HttpMessage;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p></p>
 *
 * @author : wanwei
 * @date : 2021-02-02 11:06
 **/
public class HttpProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        // 因为很明确消息格式是http的，所以才使用这个类
        // 否则还是建议使用org.apache.camel.Message这个抽象接口
        HttpMessage message = (HttpMessage) exchange.getIn();
        InputStream bodyStream = (InputStream) message.getBody();
        String inputContext = this.analysisMessage(bodyStream);
        bodyStream.close();

        // 存入到exchange的out区域
        if (exchange.getPattern() == ExchangePattern.InOut) {
            Message outMessage = exchange.getMessage();
            outMessage.setBody(inputContext + " || out");
//			System.out.println(outMessage.getBody());
        }
    }

    private String analysisMessage(InputStream bodyStream) throws IOException {
        return IOUtils.toString(bodyStream, "UTF-8");
    }

}
