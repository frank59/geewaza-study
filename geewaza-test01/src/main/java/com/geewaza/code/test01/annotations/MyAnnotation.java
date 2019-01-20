package com.geewaza.code.test01.annotations;

/**
 * Created by wangheng on 2019/1/20.
 *
 * @author wangheng
 * @date 2019/1/20
 */
public @interface MyAnnotation {

    String value() default "abc";

    String name() default "name-test";

    MyType[] type() default MyType.test01;


    public static enum MyType {
        test01,
        test02,
        test03,
        ;
    }
}
