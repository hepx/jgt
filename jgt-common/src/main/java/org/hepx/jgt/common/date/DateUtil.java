package org.hepx.jgt.common.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * @author: Koala
 * @Date: 14-8-23 下午10:56
 * @Version: 1.0
 */
public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * @Description 获取当前中国时区的TIMESTAMP日期
     * @return
     */
    public static Timestamp getSysTimestamp() {
        final TimeZone zone = TimeZone.getTimeZone("GMT+8");//获取中国时区
        TimeZone.setDefault(zone);//设置时区
        return new Timestamp((new java.util.Date()).getTime());
    }

    /**
     * 格式化日期，默认的格式为 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formateDate(Date date){
        return formatDate(date,DateFormatType.DATE);
    }
    /**
     * 格式化指定格式的日期
     * @param date 		时间
     * @param type 	日期格式,例： yyyyMMddHHmmss
     * @return String	格式后的字符串日期
     */
    public static String formatDate(Date date, DateFormatType type) {
        SimpleDateFormat format = new SimpleDateFormat(type.getValue());
        return format.format(date);
    }

    /**
     * 格式long类型日期为 Date
     * @param time	long类型日期
     * @return Date
     */
    public static Date formatDate(long time) {
        return new Date(time);
    }

    /**
     * 将字符串用指定的formatType解析成日期
     * @param time
     * @param type
     * @return
     */
    public static Date parseDate(String time,DateFormatType type){
        SimpleDateFormat format = new SimpleDateFormat(type.getValue());
        try {
            return format.parse(time);
        } catch (ParseException e) {
            logger.error("解析日期出错：" + e.getMessage());
            return null;
        }
    }

}
