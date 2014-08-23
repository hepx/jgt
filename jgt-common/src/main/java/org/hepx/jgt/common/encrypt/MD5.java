package org.hepx.jgt.common.encrypt;

import org.hepx.jgt.common.JgtConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.*;

/**
 * @author: Koala
 * @Date: 14-8-23 下午10:29
 * @Version: 1.0
 */
public class MD5 {
    private final static Logger logger = LoggerFactory.getLogger(MD5.class);

    /**
     * @param plainText 需要加密的字符串
     * @return
     * @Description 字符串加密为MD5
     * 中文加密一致通用,必须转码处理：
     * plainText.getBytes("UTF-8")
     */
    public static String toMD5(String plainText) {
        StringBuffer rlt = new StringBuffer();
        try {
            rlt.append(md5String(plainText.getBytes(JgtConstant.ENCODING)));
        } catch (UnsupportedEncodingException e) {
            logger.error(" MD5加密出错:", e.toString());
        }
        return rlt.toString();
    }

    /**
     * MD5 参数签名生成算法
     * @param params 请求参数集，所有参数必须已转换为字符串类型
     * @param secret 签名密钥
     * @return 签名
     * @throws java.io.IOException
     */
    public static String getSignature(HashMap<String, String> params, String secret) throws IOException {
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            basestring.append(param.getKey()).append("=").append(param.getValue());
        }
        basestring.append(secret);
        byte[] bytes = md5Raw(basestring.toString().getBytes(JgtConstant.ENCODING));
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }

    public static byte[] md5Raw(byte[] data) {
        byte[] md5buf = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5buf = md5.digest(data);
        } catch (Exception e) {
            md5buf = null;
            e.printStackTrace(System.err);
        }
        return md5buf;
    }

    public static String md5String(byte[] data) {
        String md5Str = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5Str = "";
            byte[] buf = md5.digest(data);
            for (int i = 0; i < buf.length; i++) {
                md5Str += byte2Hex(buf[i]);
            }
        } catch (Exception e) {
            md5Str = null;
            e.printStackTrace(System.err);
        }
        return md5Str;
    }

    public static String byte2Hex(byte b) {
        String hex = Integer.toHexString(b);
        if (hex.length() > 2) {
            hex = hex.substring(hex.length() - 2);
        }
        while (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String byte2Hex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        String hash = formatter.toString();
        formatter.close();
        return hash;
    }
}
