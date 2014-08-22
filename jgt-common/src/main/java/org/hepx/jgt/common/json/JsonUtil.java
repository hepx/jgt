package org.hepx.jgt.common.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.hepx.jgt.common.date.DateFormatType;
import org.hepx.jgt.common.exception.JgtException;

/**
 * JSON序列化工具类 --依赖 fastJson
 *
 * @author: Koala
 * @Date: 14-8-21 下午6:23
 * @Version: 1.0
 */
public class JsonUtil {

    /**
     * 序列化对象
     * @param o
     * @return
     */
    public static String objectToJson(Object o) {
        try {
            return JSON.toJSONString(o);
        } catch (Exception e) {
            throw new JgtException("不能序列化对象为Json", e);
        }
    }

    /**
     * 序列化对象
     * @param o
     * @param type 日期格式化
     * @return
     */
    public static String objectToJson(Object o,DateFormatType type) {
        return objectToJson (o,type, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 序列化对象
     * @param o
     * @param type
     * @param s
     * @return
     */
    public static String objectToJson(Object o,DateFormatType type,SerializerFeature s) {
        try {
            return  JSON.toJSONStringWithDateFormat(o,type.getValue(), s);
        } catch (Exception e) {
            throw new RuntimeException("不能序列化对象为Json", e);
        }
    }

    /**
     * 将 json 字段串转换为 对象.
     * @param json  字符串
     * @param clazz 需要转换为的类
     * @return
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            throw new JgtException("将 Json 转换为对象时异常,数据是:" + json, e);
        }
    }
}
