/**
 * 
 */
package com.betel.utlis.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ClassName:AESUtil
 * @Description:AES加密工具
 * @author:Du.hx
 * @Date:2016年6月12日上午10:43:34
 * @version 1.1
 */
public class AESUtil {

    private static final String AESKEY = "hellodhx";

    /**
     * AES加密
     * 
     * @author:Du.hx
     * @Date:2016年6月12日上午10:44:32
     * @param content
     * @return
     * @throws Exception
     */
    public static String aesEncryptToBytes(String content) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(AESKEY.getBytes());

        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

        byte buf[] = cipher.doFinal(content.getBytes("utf-8"));
        return parseByte2HexStr(buf);
    }

    /**
     * AES解密
     * 
     * @author:Du.hx
     * @Date:2016年6月12日上午10:43:45
     * @param encryptBytes
     * @return
     * @throws Exception
     */
    public static String aesDecryptByBytes(String encryptBytes) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(AESKEY.getBytes());

        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(parseHexStr2Byte(encryptBytes));

        return new String(decryptBytes);
    }

    /**
     * 将二进制转换为十六进制
     * 
     * @author:Du.hx
     * @Date:2016年6月12日上午10:44:03
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     * 
     * @author:Du.hx
     * @Date:2016年6月12日上午10:44:19
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
