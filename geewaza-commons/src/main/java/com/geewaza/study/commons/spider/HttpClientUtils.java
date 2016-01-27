package com.geewaza.study.commons.spider;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.DefaultCookieSpecProvider;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class HttpClientUtils {
	public static CloseableHttpClient getThreadSafeClient(PoolingHttpClientConnectionManager connectionManager) {
    	Registry<CookieSpecProvider> r = RegistryBuilder.<CookieSpecProvider>create()
	            .register(CookieSpecs.DEFAULT,
	                new DefaultCookieSpecProvider())
	            .build();

    	HttpHost localhost = new HttpHost("locahost", 80);
		connectionManager.setMaxPerRoute(new HttpRoute(localhost), 50);
		
	    IdleConnectionEvictor thread = new IdleConnectionEvictor(connectionManager);
		thread.start();
    	
    	CloseableHttpClient client = HttpClientBuilder
        		.create()
        		.disableAuthCaching()//禁止认证缓存
        		.disableContentCompression()//禁止内容压缩
        		.setKeepAliveStrategy(keepAliveStrat)
        		.setConnectionManager(connectionManager)
        		.setRetryHandler(myRetryHandler)
        		.setDefaultCookieSpecRegistry(r)
        		.build();
			
		return client;
    }
    
    
    private static HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
        public boolean retryRequest(
                IOException exception,
                int executionCount,
                HttpContext context) {
            if (executionCount >= 1) {
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                // Timeout
                return false;
            }
            if (exception instanceof UnknownHostException) {
                // Unknown host
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                // Connection refused            	
                return false;
            }
            if (exception instanceof SSLException) {
                // SSL handshake exception
                return false;
            }
            
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // Retry if the request is considered idempotent
                return true;
            }
            return false;
        }
    };
    
    private static ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
        @Override
        public long getKeepAliveDuration(
            HttpResponse response,
            HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    //如果服务器没有设置keep-alive这个参数，我们就把它设置�?�?
                    keepAlive = 5000;
                }
                return keepAlive;
        }
    };
    
    public static class IdleConnectionEvictor extends Thread {
        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        connMgr.closeExpiredConnections();
                        connMgr.closeIdleConnections(5, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                // terminate
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }

}
