/*
 *   ©2016 ALL Rights Reserved DHX
 *  　　   ┏┓   ┏┓
 *  　　 ┏━┛┻━━━┛┻━┓
 *   　　┃         ┃
 *   　　┃    ━    ┃
 *   　　┃  ┳┛ ┗┳  ┃
 *   　　┃         ┃
 *   　　┃    ┻    ┃
 *   　　┗━┓     ┏━┛
 *         ┃    ┃  Code is far away from bug with the animal protecting
 *         ┃    ┃    神兽保佑,代码无bug
 *         ┃    ┗━━━━━┓
 *         ┃          ┣┓
 *         ┃          ┏┛
 *         ┗┓┓┏━━━━┓┓┏┛
 *          ┃┫┫    ┃┫┫
 *          ┗┻┛    ┗┻┛
 *   ━━━━━━感觉萌萌哒━━━━━━
 *
 */

package pers.dhx.utlis;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * <p>
 * 读写Properties工具
 * </p>
 * ClassName: PropertiesUtil <br/>
 * Author: Du.Hx  <br/>
 * Date: 2017/5/10 17:22 <br/>
 * Version: 1.0 <br/>
 */
public class PropertiesUtil {

    /**
     * <p>
     * 读取配置信息
     * </p>
     * Author: Du.hx <br/>
     * Date: 2017/5/12 16:26
     *
     * @param key      属性名称
     * @param propPath 配置文件路径
     * @return 返回该属性value
     */
    public static String read(String key, String propPath) {

        String value = null;

        Properties properties = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(propPath);
            properties.load(in);
            value = properties.getProperty(key).trim();
        } catch (IOException e) {
            // ignore
        } finally {
            IOUtils.closeQuietly(in);
        }
        return value;
    }

    /**
     * <p>
     * 写入配置信息
     * <pre>
     *     1.如果该配置不存在则新增该配置
     *     2.如果该配置已存在则更新该配置
     * </pre>
     * </p>
     * Author: Du.hx <br/>
     * Date: 2017/5/12 16:27
     *
     * @param key      属性名称
     * @param value    属性value值
     * @param propPath 配置文件路径
     * @return 写入成功返回：true，失败返回：false
     */
    public static boolean write(String key, String value, String propPath) {
        Properties properties = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(propPath);
            properties.load(in);
        } catch (IOException e) {
            // ignore
            return false;
        } finally {
            IOUtils.closeQuietly(in);
        }
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(propPath);
            properties.setProperty(key, value);
            properties.store(fos, "Update '" + key + "'" + value);
        } catch (IOException e) {
            // ignore
            return false;
        } finally {
            IOUtils.closeQuietly(fos);
        }
        return true;
    }
}
