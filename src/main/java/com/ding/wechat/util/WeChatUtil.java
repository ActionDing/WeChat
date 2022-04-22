package com.ding.wechat.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author Actionding
 * @create 2022-04-22 11:37
 */
public class WeChatUtil {
    private static final String token = "wechat"; // 自己设置的Token

    /**
     * 加密/校验流程如下：
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        // 1、排序
        Arrays.sort(arr);
        // 2、拼接
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        // 3、加密
        String sha1 = getSha1(sb.toString());
        // 4、比较
        return signature.equals(sha1);
    }

    private static String getSha1(String str) {
        if (str == null) {
            return null;
        }

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            byte[] bytes = messageDigest.digest();
            int len = bytes.length;
            StringBuilder sb = new StringBuilder(len * 2);
            // 把密文转换成十六进制的字符串形式
            for (byte aByte : bytes) {
                sb.append(hexDigits[(aByte >> 4) & 0x0f]);
                sb.append(hexDigits[aByte & 0x0f]);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}