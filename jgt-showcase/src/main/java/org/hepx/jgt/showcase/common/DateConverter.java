package org.hepx.jgt.showcase.common;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * request请求时 string 转 date
 * @author: Koala
 * @Date: 14-8-13 下午3:12
 * @Version: 1.0
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }
}
