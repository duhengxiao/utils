package com.betel.utlis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @ClassName:PropertiesUtil
 * @Description:读写Properties工具
 * @author:Du.hx
 * @Date:2016年6月12日下午1:03:21
 * @version 1.0
 */
public class PropertiesUtil {

    /**
     * 读取指定属性值
     * 
     * @author:Du.hx
     * @Date:2016年6月12日下午1:03:28
     * @param key
     * @param propPath
     * @return
     * @throws IOException
     */
    public static String read(String key, String propPath) throws IOException {

        String value = null;

        Properties pps = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(propPath);
            pps.load(in);
            value = pps.getProperty(key).trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return value;
    }

    /**
     * 新增或修改属性值
     * 
     * @author:Du.hx
     * @Date:2016年6月12日下午1:03:45
     * @param key
     * @param value
     * @param propPath
     * @throws IOException
     */
    public static void write(String key, String value, String propPath) throws IOException {
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(propPath);
            props.load(in);
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(propPath);
            props.setProperty(key, value);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + key + "'" + value);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
