package org.hepx.jgt.common.convertor;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean和Map之间转换
 * User: hepanxi
 * Date: 14-11-20
 * Time: 下午3:01
 */
public class BeanConvertor {

    private static String  EXCLUDE_TYPE="class";

    /**
     * 将JavaBean 转换成 MAP
     * 注：需要转换的JavaBean的属性的getter/setter方法必须满足javaBean命名规范
     * @param bean
     * @return
     */
    public static Map<String, Object> bean2Map(Object bean) {
        return bean2Map(bean,false);
    }

    public static Map<String,Object> bean2Map(Object bean,boolean ignoreNull){
        Map<String,Object> map = null;
        try {
            if(bean!=null){
                map = new HashMap<String,Object>();
                //1：通过JAVA内省来获得参数的Bean属性信息
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                //2：获得属性的描述信息
                PropertyDescriptor [] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for(PropertyDescriptor p : propertyDescriptors){
                    String fieldName = p.getName(); //字段名
                    if(!fieldName.equals(EXCLUDE_TYPE)){ //排除class
                        Method method = p.getReadMethod(); //获得getXXX方法
                        Object fieldValue = method.invoke(bean); //调用getXXX获得值
                        if(ignoreNull){
                            if(fieldValue != null){
                                map.put(fieldName,fieldValue);
                            }
                        }else{
                            map.put(fieldName,fieldValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将MAP 转换成 JavaBean
     * @param map
     * @param bean
     */
    public static void map2Bean(Map<String,Object>map,Object bean){
        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor [] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor p : propertyDescriptors){
                String fieldName = p.getName();
                if(map.containsKey(fieldName)){
                    Object value = map.get(fieldName);
                    Method setter = p.getWriteMethod();
                    setter.invoke(bean,value);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
