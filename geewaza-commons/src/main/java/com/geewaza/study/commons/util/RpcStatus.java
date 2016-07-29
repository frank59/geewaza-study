package com.geewaza.study.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 统计调用状态
 */
public class RpcStatus {
    //记录失败次数
    private AtomicLong failTimes = new AtomicLong(0);
    //记录调用次数
    private AtomicLong invokeTimes = new AtomicLong(0);
    //降级-阈值
    private double failedRate = 0.1;
    //降级-最少条件
    private double minTimes = 10;
    private AtomicLong currentTime = new AtomicLong(System.currentTimeMillis() / 60000);
    private AtomicBoolean isFailed = new AtomicBoolean(false);
    private ThreadLocal<Boolean> fail = new ThreadLocal<Boolean>();
    //自动功能降级是否打开？ 默认关闭
    private boolean enabled = false;
    private Logger log = LoggerFactory.getLogger(getClass());
    private String name;
    private boolean mail = true;
    private boolean sms = true;

    public RpcStatus(Logger log) {
        this.log = log;
        fail.set(false);
    }

    public RpcStatus(double failedRate, double minTimes, boolean enabled) {
        fail.set(false);
        this.failedRate = failedRate;
        this.minTimes = minTimes;
        this.enabled = enabled;
    }

    public boolean isFailed() {
        if (fail.get() == null) {
            fail.set(false);
            return false;
        }
        if (fail.get()) {
            fail.set(false);
            return true;
        }
        return false;
    }

    public void addFailTimes() {
        fail.set(true);
        if (enabled) {
            failTimes.incrementAndGet();
        }
    }

    public void addInvokeTimes() {
        if (enabled) {
            invokeTimes.incrementAndGet();
        }
    }

    public boolean isWorked() {
        if (!enabled) {
            return true;
        }
        if (isFailed.get() && System.currentTimeMillis() % 100 == 0) {
            return true;
        }
        if (isFailed.get()) {
            fail.set(true);
            return false;
        }
        return true;
    }

    public void checkStatus(String name) {
        if (!enabled) {
            return;
        }
        long newTime = System.currentTimeMillis() / 60000;
        if ((newTime > currentTime.get()) && (invokeTimes.get() > minTimes)) {
            double percent = failTimes.get() * 1.0 / invokeTimes.get();
            if (percent > failedRate) {
                if (isFailed.get()) {
                    log.error(new StringBuilder(name).append(" level still down. ").append(invokeTimes)
                            .append(" invoke, ").append(failTimes).append(" fail").toString());
                    if (mail) {
                        try {
                            StringBuilder content = new StringBuilder();
                            content.append(name);
                            content.append("功能降级,still down").append(invokeTimes).append(" invoke, ");
                            content.append(failTimes).append(" fail @").append(DataFormat.currentDate());
//                            content.append(" from ").append(NetUtil.getFirstLocalIP());
//                            EMail.send(PropertiesUtil.getStringArray("rpc.mail"), name + "still down", content.toString() , "功能降级");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    log.error(new StringBuilder(name).append(" level down. ").append(invokeTimes)
                            .append(" invoke, ").append(failTimes).append(" fail").toString());
                    isFailed.set(true);
                    if (sms) {
//                        SmsSender.I.sendMerge(name, "功能降级", name, DataFormat.currentDate(), 3, 50, PropertiesUtil.getStringArray("rpc.sms"));
                    }
                    if (mail) {
                        try {
                            StringBuilder content = new StringBuilder();
                            content.append(name);
                            content.append("功能降级,still down").append(invokeTimes).append(" invoke, ");
                            content.append(failTimes).append(" fail @").append(DataFormat.currentDate());
//                            content.append(" from ").append(NetUtil.getFirstLocalIP());
//                            EMail.send(PropertiesUtil.getStringArray("rpc.mail"), name + "功能降级", content.toString(), "功能降级");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                if (isFailed.get()) {
                    log.error(new StringBuilder(name).append(" level back to normal. ").append(invokeTimes)
                            .append(" invoke, ").append(failTimes).append(" fail").toString());
                    if (sms) {
//                        SmsSender.I.sendMerge(name, "恢复", name, DataFormat.currentDate(), 3, 50, PropertiesUtil.getStringArray("rpc.sms"));
                    }
                    if (mail) {
                        try {
                            StringBuilder content = new StringBuilder();
                            content.append(name);
                            content.append("功能恢复").append(invokeTimes).append(" invoke, ");
                            content.append(failTimes).append(" fail @").append(DataFormat.currentDate());
//                            content.append(" from ").append(NetUtil.getFirstLocalIP());
//                            EMail.send(PropertiesUtil.getStringArray("rpc.mail"), name + "功能恢复", content.toString(), "功能降级");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                isFailed.set(false);
            }
            if (log.isInfoEnabled()) {
                log.info(new StringBuilder(name).append(" [info]. ").append(invokeTimes)
                        .append(" invoke, ").append(failTimes).append(" fail").toString());
            }
            currentTime.set(newTime);
            failTimes.set(0);
            invokeTimes.set(0);
        }
    }

    public void setFailedRate(double failedRate) {
        this.failedRate = failedRate;
    }

    public void setMinTimes(double minTimes) {
        this.minTimes = minTimes;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMail() {
        return mail;
    }

    public void setMail(boolean mail) {
        this.mail = mail;
    }

    public boolean isSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }
}
