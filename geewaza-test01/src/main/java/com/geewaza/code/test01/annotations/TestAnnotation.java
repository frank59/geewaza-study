package com.geewaza.code.test01.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * Created by wangheng on 2019/1/20.
 *
 * @author wangheng
 * @date 2019/1/20
 */
public class TestAnnotation {

    @Test
    public void test01() {
        Class<User> clazz = User.class;

        //获得clazz（User）里面所有方法信息
        Method[] methods = clazz.getDeclaredMethods();

        //获得clazz（User）里面所有属性信息
        Field[] declaredFields = clazz.getDeclaredFields();

        System.out.println("methods注解个数："+methods.length);
        System.out.println("declaredFields注解个数："+declaredFields.length);

        //遍历循环所有方法信息
        for (Method method : methods) {
            //判断method是否含有指定元素的注解
            if (method.isAnnotationPresent(ReqMapping.class)) {
                //返回当前方法上的注解对象
                ReqMapping reqMapping = method.getAnnotation(ReqMapping.class);
                //获得注解的值
                System.out.println("方法注解值："+reqMapping.method());

                //如果一个注解有多个值，通过遍历取出（特别注意：reqMapping.val()，这个val()是你定义注解的成员）
                String[] values = reqMapping.val();
                for (String value : values) {
                    System.out.println(value);
                }
            }
        }

        //获得类里面所有方法的注解信息
        for (Field declaredField : declaredFields) {
            if(declaredField.isAnnotationPresent(ReqValue.class)){
                ReqValue reqValue = declaredField.getAnnotation(ReqValue.class);
                System.out.println("属性注解值1："+reqValue.value1());
                System.out.println("属性注解值2："+reqValue.value2());
            }
        }

        //获得类上的所有注解
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            System.out.println("类注解值:"+ declaredAnnotation);
        }

    }
}
