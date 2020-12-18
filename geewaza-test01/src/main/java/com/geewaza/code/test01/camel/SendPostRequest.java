package com.geewaza.code.test01.camel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * apache httpclient 工具类
 * 支持对 http https 接口调用
 * <p>
 * 1.GET 请求
 * 2.POST 请求
 * </P>
 */

public class SendPostRequest {

    public static void main(String[] args) {
        HttpDelegate httpDelegate = new HttpDelegate(false);
        String[][] tests = {
                {"http://localhost:8282/doHelloWorld", "text/xml", "UTF-8", "body"}
        };
        for (String[] test: tests) {
            //开始进行回调
            System.out.println("回调信息," + test[3]);
            String response = httpDelegate.executePost(test[0], test[1], test[2], test[3]);
            System.out.println("response," + response);
        }
    }

    public static class HttpDelegate {
        private int timeOut = 10000;//指定超时时间
        private CloseableHttpClient httpClient;
        private RequestConfig requestConfig;//设置请求参数

        /**
         * 对象被创建时执行
         */
        public HttpDelegate(Boolean isHttps) {
            httpClient = this.getHttpClient(isHttps);
            this.initRequestConfig();//初始化请求配置
        }

        /**
         * 发送post请求
         *
         * @param url         请求地址
         * @param contentType 请求类型
         * @param encoding    编码格式
         * @param param       请求参数
         * @return
         */
        public String executePost(String url, String contentType, String encoding, String param) {
            HttpPost httpPost = new HttpPost(url);
            try {
                httpPost.setConfig(requestConfig);
                this.initMethodParam(httpPost, contentType, encoding);
                if (param != null) {
                    httpPost.setEntity(new StringEntity(param, encoding));//设置请求体
                }
                return this.execute(httpPost, httpClient);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            } finally {
                httpPost.releaseConnection();
            }
        }

        /**
         * 发送get请求
         *
         * @param url         请求地址
         * @param contentType 请求类型
         * @param encoding    编码格式
         * @return
         */
        public String executeGet(String url, String contentType, String encoding) {
            HttpGet httpGet = new HttpGet(url);
            try {
                httpGet.setConfig(requestConfig);
                this.initMethodParam(httpGet, contentType, encoding);
                return this.execute(httpGet, httpClient);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            } finally {
                httpGet.releaseConnection();
            }
        }

        /**
         * 通用方法，用来发送post或get请求
         *
         * @param method     请求类型
         * @param httpClient
         * @return
         * @throws RuntimeException
         * @throws IOException
         */
        private String execute(HttpUriRequest method, CloseableHttpClient httpClient) throws RuntimeException, IOException {
            try (CloseableHttpResponse response = httpClient.execute(method)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, method.getFirstHeader(HttpHeaders.CONTENT_ENCODING).getValue());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        private void initMethodParam(HttpUriRequest method, String contentType, String encoding) {
            if (contentType != null) {
                method.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
            }
            method.setHeader(HttpHeaders.CONTENT_ENCODING, encoding);
            method.setHeader(HttpHeaders.TIMEOUT, "60000");
        }

        /**
         * 设置初始值
         */
        private void initRequestConfig() {
            requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
        }

        /**
         * 获取主体类:
         * isHttps 区分https 和 https
         *
         * @param isHttps
         * @return
         */
        private CloseableHttpClient getHttpClient(boolean isHttps) {
            CloseableHttpClient chc = null;
            try {
                if (isHttps) {
                    TrustManager[] trustManagers = new TrustManager[1];
                    trustManagers[0] = new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    };
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(new KeyManager[0], trustManagers, new SecureRandom());
                    SSLContext.setDefault(sslContext);
                    sslContext.init(null, trustManagers, null);
                    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                    chc = HttpClients.custom().setSSLSocketFactory(sslsf).build();
                } else {
                    chc = HttpClients.custom().build();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            return chc;
        }

        /**
         * 关闭链接
         */
        public void destroy() {
            System.out.println("方法被执行");
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
}


