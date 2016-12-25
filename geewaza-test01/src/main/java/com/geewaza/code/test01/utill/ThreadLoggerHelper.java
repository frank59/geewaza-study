package com.geewaza.code.test01.utill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangh on 2016/12/25.
 */
public class ThreadLoggerHelper {

    private static Logger logger = LoggerFactory.getLogger(ThreadLoggerHelper.class);

    /**
     * 用于绑定线程与logger
     */
    private static ThreadLocal<Logger> loggerMap = new ThreadLocal<Logger>();

    /**
     * 通过logger名称来绑定线程与logger
     * @param loggerName
     */
    public static void bind(String loggerName) {
        Logger threadLogger = LoggerFactory.getLogger(loggerName);
        loggerMap.set(threadLogger);
    }

    /**
     * 销毁线程对应的logger
     */
    public static void destroy(){
        if (loggerMap.get() != null) {
            loggerMap.remove();
        }
    }

    /**
     * 获取当前线程的logger
     * @return
     */
    public static Logger get() {
        Logger threadLogger = loggerMap.get();
        if (threadLogger == null) {
            return logger;
        } else {
            return threadLogger;
        }
    }

    public static abstract class IndependentLoggerRunner implements Runnable {

        private String loggerName;

        public void setLoggerName(String loggerName) {
            this.loggerName = loggerName;
        }

        public String getLoggerName() {
            return loggerName;
        }

        public void run() {
            try {
                ThreadLoggerHelper.bind(loggerName);
                runJob();
            } finally {
                ThreadLoggerHelper.destroy();
            }
        }

        protected abstract void runJob();
    }

}
