/**
 * 
 */
package com.betel.utlis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

/**
 * @ClassName:SendHttpUtil
 * @Description:
 * @author:Du.hx
 * @Date:2016年8月23日下午10:53:29
 * @version 1.0
 */
public class SendHttpUtil {

    private static final String LINE_BREAK = "\r\n";

    private static final String BOUNDARY_PREFIX = "--";

    private static final String BOUNDARY = UUID.randomUUID().toString();

    public static void doPost(String urlPath, File file) {
        OutputStream out = null;
        DataInputStream in = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置为POST
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求头参数
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            out = new DataOutputStream(conn.getOutputStream());

            StringBuilder sb = new StringBuilder();
            sb.append(BOUNDARY_PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINE_BREAK);
            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data;name=\"photo\";filename=\"" + file.getName() + "\"" + LINE_BREAK);
            sb.append("Content-Type:application/octet-stream");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(LINE_BREAK);
            sb.append(LINE_BREAK);
            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());
            // 数据输入流,用于读取文件数据
            in = new DataInputStream(new FileInputStream(file));
            byte[] bufferOut = new byte[1024];
            int bytes = 0;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            // 最后添加换行
            out.write(LINE_BREAK.getBytes());

            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (LINE_BREAK + BOUNDARY_PREFIX + BOUNDARY + BOUNDARY_PREFIX + LINE_BREAK).getBytes();
            // 写上结尾标识
            out.write(end_data);
            out.flush();

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

    }

    public static void doGet() {

    }

}
