//package com.geewaza.code.test01.utill;
//
//import com.geewaza.code.test01.gcash.event.bean.Event_params;
//import com.geewaza.code.test01.gcash.event.bean.GcashEventLog;
//import com.geewaza.code.test01.gcash.event.bean.User_properties;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by wangheng on 2017/11/29.
// */
//public class ReflectUtil {
//
//
//    public static void buildMarkdownTable() throws IllegalAccessException {
//        /*
//         * 实列化类 方法2
//         */
//        GcashEventLog bean = new GcashEventLog();
//        // 得到类对象
//        @SuppressWarnings("rawtypes")
//        Class cls = (Class) bean.getClass();
//
//        String tableHead = "|字段名|数据类型|说明|\n"
//            + "| --- | --- | --- |";
//        String tableLineTmp = "|%s|%s||";
//
//        System.out.println(tableHead);
//        Field[] fs = cls.getDeclaredFields();
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            //System.out.println(f.toString());
//            f.setAccessible(true); // 设置些属性是可以访问的
//            String type = f.getType().getSimpleName();
//            String line = String.format(tableLineTmp, f.getName(), type);
//            System.out.println(line);
//        }
//    }
//    public static void buildMarkdownTable(Object bean)
//        throws IllegalAccessException, InstantiationException {
//
//        if (bean == null) {
//            return ;
//        }
//        Class cls = bean.getClass();
//        System.out.println("#### " + cls.getSimpleName());
//        String tableHead = "|字段名|数据类型|说明|\n"
//            + "| --- | --- | --- |";
//        String tableLineTmp = "|%s|%s||";
//
//        System.out.println(tableHead);
//        Field[] fs = cls.getDeclaredFields();
//
//        List<Field> custFields = new ArrayList<>();
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            String type = f.getType().getName();
//            String typeClassName = f.getType().getSimpleName();
//            //System.out.println(type);
//            if (!type.startsWith("java") && type.contains(".") && !type.startsWith("com.alibaba.fastjson")) {
//                custFields.add(f);
//                //buildMarkdownTable(f.getType().getSimpleName(), f.getType().newInstance());
//                typeClassName = String.format("[%s](#%s)", typeClassName, typeClassName.toLowerCase());
//            }
//            f.setAccessible(true); // 设置些属性是可以访问的
//            String line = String.format(tableLineTmp, f.getName(), typeClassName);
//            System.out.println(line);
//        }
//        if (!custFields.isEmpty()) {
//            for (Field f : custFields) {
//                buildMarkdownTable(f.getType().newInstance());
//            }
//        }
//
//    }
//
//
//    public static void buildSetter() throws IllegalAccessException {
//        /*
//         * 实列化类 方法2
//         */
//        GcashEventLog bean = new GcashEventLog();
//
//        // 得到类对象
//        @SuppressWarnings("rawtypes")
//        Class cls = (Class) bean.getClass();
//
//        /*
//         * 得到类中的所有属性集合
//         */
//        Field[] fs = cls.getDeclaredFields();
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            f.setAccessible(true); // 设置些属性是可以访问的
//            // Object val = f.get(bean);// 得到此属性的值
//
//            // System.out.println("name:" + f.getName() + "\t value = " + val);
//
//            String type = f.getType().toString();// 得到此属性的类型
//            if (type.endsWith("String")) {
//                // System.out.println(f.getType() + "\t是String");
//                f.set(bean, "12");        // 给属性设值
//            } else if (type.endsWith("int") || type.endsWith("Integer")) {
//                // System.out.println(f.getType() + "\t是int");
//                f.set(bean, 12);       // 给属性设值
//            } else {
//                // System.out.println(f.getType() + "\t");
//            }
//
//        }
//
//        /*
//         * 得到类中的方法
//         */
//        Method[] methods = cls.getMethods();
//        for (int i = 0; i < methods.length; i++) {
//            Method method = methods[i];
//            // if (method.getName().startsWith("get")) {
//            // System.out.print("methodName:" + method.getName() + "\t");
//            // System.out.println("value:" + method.invoke(bean));// 得到get 方法的值
//            // }
//
//            if (method.getName().startsWith("set")) {
//                System.out.print(firstToLower(cls.getSimpleName()) + "." + method.getName() + "(source.get" + method.getName().substring(3) + "());\n");
//            }
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
////        buildSetter();
//        buildMarkdownTable(new User_properties());
//    }
//
//    /**
//     * @param val
//     * @return
//     */
//    public static String firstToLower(String val) {
//        return val.substring(0, 1).toLowerCase() + val.substring(1);
//    }
//    /**
//     * @param val
//     * @return
//     */
//    public static String firstToUper(String val) {
//        return val.substring(0, 1).toUpperCase() + val.substring(1);
//    }
//}
