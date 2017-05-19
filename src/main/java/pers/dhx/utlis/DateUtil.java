package com.betel.utlis;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:DateUtil
 * @Description:时间工具类
 * @author:Du.hx
 * @Date:2016年6月12日上午11:44:40
 * @version 1.0.0
 */
public class DateUtil {

    /** 缺省日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /** 缺省时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /** 缺省长日期格式,精确到秒 */
    public static final String DEFAULT_DATETIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获得当前日期，如2016-3-31
     * 
     * @author:dhx
     * @Date:2016年6月8日下午3:00:53
     * @return
     */
    public static String getDate() {
        return today(DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取当前时间，如 14:44:48
     * 
     * @author:dhx
     * @Date:2016年6月8日下午3:01:05
     * @return
     */
    public static String getTime() {
        return today(DEFAULT_TIME_FORMAT);
    }

    /**
     * 获取当前日期时间
     * 
     * @author:dhx
     * @Date:2016年6月8日下午3:01:15
     * @return
     */
    public static String getDateTime() {
        return today(DEFAULT_DATETIME_FORMAT_SEC);
    }

    /**
     * 根据输入的格式得到当前日期的字符串
     * 
     * @author:dhx
     * @Date:2016年6月8日下午3:01:24
     * @param strFormat
     * @return
     */
    public static String today(String strFormat) {
        return toString(new Date(), strFormat);
    }

    /**
     * 根据格式将日期转为字符串
     * 
     * @author:dhx
     * @Date:2016年6月8日下午3:01:32
     * @param date
     * @param format
     * @return
     */
    public static String toString(Date date, String format) {
        return getSimpleDateFormat(format).format(date);
    }

    /**
     * 根据格式将字符串转换成Date
     * 
     * @author:dhx
     * @Date:2016年6月8日下午3:01:42
     * @param dateStr
     * @param format
     * @return
     */
    public static Date toDate(String dateStr, String format) {
        try {
            return getSimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 将字符串转换成Time
     * 
     * @author:dhx
     * @Date:2016年6月8日下午3:01:52
     * @param timeStr
     * @return
     */
    public static Time toTime(String timeStr) {
        try {
            String dateStr = "2016-05-12" + " " + timeStr;
            SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SEC);
            Date d = format.parse(dateStr);
            return new Time(d.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return new Time(new Date().getTime());
        }
    }

    /**
     * 将格式字符串转为格式对象
     * 
     * @author:dhx
     * @Date:2016年6月8日下午3:02:04
     * @param strFormat
     * @return
     */
    private static SimpleDateFormat getSimpleDateFormat(String strFormat) {
        if (strFormat != null && !"".equals(strFormat.trim())) {
            return new SimpleDateFormat(strFormat);
        } else {
            return new SimpleDateFormat();
        }
    }

}
