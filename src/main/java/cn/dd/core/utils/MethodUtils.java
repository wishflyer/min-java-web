package cn.dd.core.utils;

import java.lang.reflect.Method;

/**
 * Created by czdujb on 2015/10/28.
 */
public class MethodUtils {
    public static String getFullMethodName(Method method) {
        return String.format("%s.%s", method.getDeclaringClass().getName(),method.getName());
    }
/*
    public static String getFullMethodName(String interfaceName, Method method) {
        return String.format("%s.%s", interfaceName, method.getName());
    }*/
}
