package com.geewaza.study.camel.http;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p></p>
 *
 * @author : wanwei
 * @date : 2021-02-02 11:12
 **/
public class TestClient {

    public static void main(String[] args) {
        URL url = null;
        HttpURLConnection http = null;

        try {

            url = new URL("http://0.0.0.0:8282/doHelloWorld");

            for (int i = 0; i < 1; i++) {
                System.out.println("http post start !!!");
                Long startTime = System.currentTimeMillis();

                http = (HttpURLConnection) url.openConnection();

                // ************************************************************
                JSONObject authorityJson = new JSONObject();
                // 用户身份证号码
                authorityJson.put("userid", "222222222222222");
                // 用户姓名
                authorityJson.put("username", "VIP_USER");
                // 用户单位
                authorityJson.put("userdept", "VIP");

                JSONObject queryInfoJson = new JSONObject();
                // 测试用
                queryInfoJson.put("source", "60155");
                queryInfoJson.put("condition", "FIRSTKEY = '320103671118051'");
                queryInfoJson.put("starttime", "");
                queryInfoJson.put("endtime", "");

                JSONObject requestJson = new JSONObject();
                requestJson.put("authority", authorityJson);
                requestJson.put("queryInfo", queryInfoJson);
                // ************************************************************

                StringBuffer sb = new StringBuffer();
                sb.append(requestJson.toString());

                String result = HttpClient.doPost(sb.toString(), 30000000, http);

                System.out.println("http post end cost :" + (System.currentTimeMillis() - startTime) + "ms");
                System.out.println("Response:");
                System.out.println(result);

                Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
