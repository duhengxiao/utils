/**
 * 
 */
package com.betel.utlis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:StringUtil
 * @Description:
 * @author:Du.hx
 * @Date:2016年6月12日上午10:47:25
 * @version 1.0
 */
public class StringUtil {

    /**
     * 字符串非空判断
     * 
     * @author:Du.hx
     * @Date:2016年6月12日上午11:06:56
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 根据指定字符分割字符串并生成list
     * 
     * @author:Du.hx
     * @Date:2016年6月12日上午11:14:35
     * @param str
     * @param symbol:分隔符
     * @return
     */
    public static List<String> splitStr2List(String str, String symbol) {
        List<String> list = new ArrayList<String>();
        if (!isEmpty(str) && null != symbol) {
            String[] tt = str.split(symbol);
            list.addAll(Arrays.asList(tt));
        }
        return list;
    }

    /**
     * 根据指定字符分割字符串并生成array
     * 
     * @author:Du.hx
     * @Date:2016年6月12日上午11:41:10
     * @param str
     * @param symbol
     * @return
     */
    public static String[] splitStr2Array(String str, String symbol) {
        if (!isEmpty(str) && null != symbol) {
            return str.split(symbol);
        }
        return null;
    }

    /**
     * 把字符串list用指定的符号连接成一个字符串
     * 
     * @author:Du.hx
     * @Date:2016年6月12日上午11:17:09
     * @param list
     * @param symbol:连接符
     * @return
     */
    public static String connectStr(List<String> list, String symbol) {
        String result = "";
        if (null != list && null != symbol) {
            for (String str : list) {
                if (str.trim().length() > 0)
                    result += (str + symbol);
            }
            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    /**
     * 把字符串arrray用指定的符号连接成一个字符串
     * 
     * @author:Du.hx
     * @Date:2016年6月12日上午11:41:35
     * @param array
     * @param symbol
     * @return
     */
    public static String connectString(String[] array, String symbol) {
        String result = "";
        if (null != array && null != symbol) {
            for (String temp : array) {
                if (null != temp && temp.trim().length() > 0)
                    result += (temp + symbol);
            }
            if (result.length() > 1 && !isEmpty(symbol)) {
                result = result.substring(0, result.length() - symbol.length());
            }
        }
        return result;
    }

}
