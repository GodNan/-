package com.css.bdpfnew.util;

/**
 * @Auther: GodNan
 * @Date: 2018/6/22 10:08
 * @Version: 1.0
 * @Description:
 *  参数+token+时间戳加密
 */
public class SignatureUtil {

    public static String generateToBody(String parameters, String token, Long timestamp) {
        String source = "parameters=" + parameters + "&token=" + token + "&timestamp=" + timestamp;
        return Md5Util.MD5Encode(source.replaceAll(" ", ""));
    }

    public static String generateToUri(String requestURI, String token, Long timestamp) {
        String source = requestURI + "&token=" + token + "&timestamp=" + timestamp;
        return Md5Util.MD5Encode(source.replaceAll(" ", ""));
    }
}
