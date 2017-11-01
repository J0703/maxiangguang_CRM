package com.lanou.hr.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  密码加密工具类
 * Created by dllo on 17/10/27.
 */
public class EncryptUtil {

    public static String getMD5Value(String value){

        try {
            // 获取JDK消息摘要算法工具类
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            // 加密 十进制
            byte[] md52Dec = messageDigest.digest(value.getBytes());

            // 将十进制封装为BigInteger 第一个为符号位
            BigInteger bigInteger = new BigInteger(1, md52Dec);

            // 将十进制转换为十六进制
            return bigInteger.toString(16);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
