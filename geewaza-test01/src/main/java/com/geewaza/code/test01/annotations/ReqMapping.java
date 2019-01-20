package com.geewaza.code.test01.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangheng on 2019/1/20.
 *
 * @author wangheng
 * @date 2019/1/20
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqMapping {

    ReqMethod [] method() default {};

    String[] val() default "";

    public static enum ReqMethod {
        GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
    }

}
