package org.hepx.jgt.common.encrypt;

import org.hepx.jgt.common.JgtConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 对称加密
 *
 * @author koala
 * @Date 2014-8-23
 */
public class AES extends Encrypt {
    private static final Logger LOGGER = LoggerFactory.getLogger(AES.class);
    private static final String ALGORITHM = "AES";
    SecretKeySpec secretKey;

    public AES() {
        setKey(JgtConstant.SECRET_KEY);//secret key
    }

    public AES(String str) {
        setKey(str);//generate secret key
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    /**
     * generate KEY
     */
    public void setKey(String strKey) {
        try {
            byte[] bk = MD5.md5Raw(strKey.getBytes(JgtConstant.ENCODING));
            this.secretKey = new SecretKeySpec(bk, ALGORITHM);
        } catch (Exception e) {
            LOGGER.error("Encrypt setKey is exception:", e.getMessage());
        }
    }

    /**
     * @param str
     * @return
     * @Description AES encrypt
     */
    public String encryptAES(String str) {
        byte[] encryptBytes = null;
        String encryptStr = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            encryptBytes = cipher.doFinal(str.getBytes());
            if (encryptBytes != null) {
                encryptStr = Base64Util.encryptBASE64(encryptBytes);
            }
        } catch (Exception e) {
            LOGGER.error("Encrypt encryptAES is exception:", e.getMessage());
        }
        return encryptStr;
    }

    /**
     * @param str
     * @return
     * @Description AES decrypt
     */
    public String decryptAES(String str) {
        byte[] decryptBytes = null;
        String decryptStr = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            byte[] scrBytes = Base64Util.decryptBASE64(str);
            decryptBytes = cipher.doFinal(scrBytes);
        } catch (Exception e) {
            LOGGER.error("Encrypt decryptAES is exception:", e.getMessage());
        }
        if (decryptBytes != null) {
            decryptStr = new String(decryptBytes);
        }
        return decryptStr;
    }

    /**
     * AES encrypt
     */
    @Override
    public String encrypt(String value, String key) throws Exception {
        return this.encryptAES(value);
    }

    /**
     * AES decrypt
     */
    @Override
    public String decrypt(String value, String key) throws Exception {
        return this.decryptAES(value);

    }
}