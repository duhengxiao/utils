/**
 * 
 */
package com.betel.utlis.test;

import com.betel.utlis.encrypt.AESUtil;

/**
 * ClassName:AESTest
 * 
 * @author:dhx
 * @Description:
 * @Date:2016年6月8日下午1:45:23
 * @since 1.0.0.0
 */
public class AESTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String content = "测试字符串";
		System.out.println("加密前：" + content);

		String encrypt = AESUtil.aesEncryptToBytes(content);
		System.out.println("加密后：" + encrypt);

		String decrypt = AESUtil.aesDecryptByBytes(encrypt);
		System.out.println("解密后：" + decrypt);
	}

}
