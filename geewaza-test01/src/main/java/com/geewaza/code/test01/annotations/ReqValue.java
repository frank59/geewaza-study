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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqValue {

    String value1() default "";

    String value2() default "";
}
