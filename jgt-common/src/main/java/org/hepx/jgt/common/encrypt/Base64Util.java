package org.hepx.jgt.common.encrypt;

import java.io.*;
import java.util.Base64;

/**
 * BASE64编码解码工具包
 * 依赖bcprov-jdk14-1.48.jar
 *
 * @author koala
 */
public class Base64Util {

    /**
     * 文件读取缓冲区大小
     */
    private static final int CACHE_SIZE = 1024;

    /**
     * BASE64字符串解码为二进制数据
     *
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64.getBytes());
    }

    /**
     * 二进制数据编码为BASE64字符串
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes));
    }

    /**
     * 将文件编码为BASE64字符串
     * 大文件慎用，可能会导致内存溢出
     *
     * @param filePath 文件绝对路径
     * @return
     * @throws Exception
     */
    public static String encodeFile(String filePath) throws Exception {
        byte[] bytes = fileToByte(filePath);
        return encode(bytes);
    }

    /**
     * BASE64字符串转回文件
     *
     * @param filePath 文件绝对路径
     * @param base64   编码字符串
     * @throws Exception
     */
    public static void decodeToFile(String filePath, String base64) throws Exception {
        byte[] bytes = decode(base64);
        byteArrayToFile(bytes, filePath);
    }

    /**
     * 文件绝对路径
     *
     * @param linuxDir linux存放目录
     * @param winDir   win存放目录
     * @param fileName 文件名
     * @return String
     */
    public static String filePath(String linuxDir, String winDir, String fileName) {
        StringBuffer bf = new StringBuffer();
        if ("\\".equals(File.separator)) {
            //windows
            bf.append(winDir);
        } else if ("/".equals(File.separator)) {
            //Linux
            bf.append(linuxDir);
        }
        bf.append(File.separator);
        bf.append(fileName);
        return bf.toString();
    }

    /**
     * 文件转换为二进制数组
     *
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    public static byte[] fileToByte(String filePath) throws Exception {
        byte[] data = new byte[0];
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
            out.close();
            in.close();
            data = out.toByteArray();
        }
        return data;
    }

    /**
     * 二进制数据写文件
     *
     * @param bytes    二进制数据
     * @param filePath 文件生成目录
     */
    public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {
        InputStream in = new ByteArrayInputStream(bytes);
        File destFile = new File(filePath);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        destFile.createNewFile();
        OutputStream out = new FileOutputStream(destFile);
        byte[] cache = new byte[CACHE_SIZE];
        int nRead = 0;
        while ((nRead = in.read(cache)) != -1) {
            out.write(cache, 0, nRead);
            out.flush();
        }
        out.close();
        in.close();
    }

}
