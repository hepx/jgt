package org.hepx.jgt.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.hepx.jgt.common.exception.JgtException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * JSON序列化工具类 --依赖 fastJson
 *
 * @author: Koala
 * @Date: 14-8-21 下午6:23
 * @Version: 1.0
 */
public class JsonUtil {

    private static Gson gson = null;
    static {
        gson= new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * 序列化对象
     * @param o
     * @return
     */
    public static String objectToJson(Object o) {
        try {
            return gson.toJson(o);
        } catch (Exception e) {
            throw new JgtException("不能序列化对象为Json", e);
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
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            throw new JgtException("将 Json 转换为对象时异常,数据是:" + json, e);
        }
    }

    /**
     * Object 转成 json
     *
     * @param src
     * @return String
     */
    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    /**
     * json 转成 特定的cls的Object
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * json 转成 特定的 rawClass<classOfT> 的Object
     *
     * @param json
     * @param classOfT
     * @param argClassOfT
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT, Class argClassOfT) {
        Type type = new ParameterizedType4ReturnT(classOfT, new Class[]{argClassOfT});
        return gson.fromJson(json, type);
    }

    public static class ParameterizedType4ReturnT implements ParameterizedType {
        private final Class raw;
        private final Type[] args;
        public ParameterizedType4ReturnT(Class raw, Type[] args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }
        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }
        @Override
        public Type getRawType() {
            return raw;
        }
        @Override
        public Type getOwnerType() {return null;}
    }

    /**
     * json 转成 特定的cls的list
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> List<T> fromJsonList(String json, Class<T> classOfT) {
        return gson.fromJson(
                json,
                new TypeToken<List<T>>() {
                }.getType()
        );
    }
}
