package com.codeplay.methodcallpro.util;

import java.util.UUID;

/**
 * @author coldilock
 */
public class StringUtils {
    public static boolean checkNodeName(String str){
        return !str.isEmpty() && !str.startsWith(".") && !str.startsWith("UnsolvedType.");
        // return !str.isEmpty() && !str.startsWith(".");
    }

    public static String createId(){
        Integer orderId = UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        return String.valueOf(orderId);

         // return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
