package org.hepx.jgt.common.encrypt;

/**
 * 对称加密父类
 * @author koala
 * @Date 2014-8-23
 */
public abstract class Encrypt {

    /**
     * 字符串内容加密
     * @param value 加密内容
     * @param key   密钥
     * @return
     * @throws Exception
     */
    public abstract String encrypt(String value, String key) throws Exception;

    /**
     * 字符串内容解密
     * @param value 解密内容
     * @param key   密钥
     * @return
     * @throws Exception
     */
    public abstract String decrypt(String value, String key) throws Exception;
}
