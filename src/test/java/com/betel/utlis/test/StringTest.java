/**
 * 
 */
package com.betel.utlis.test;

import java.util.List;

import com.betel.utlis.StringUtil;

/**
 * @ClassName:StringTest
 * @Description:
 * @author:Du.hx
 * @Date:2016年6月12日上午11:09:00
 * @version 1.0
 */
public class StringTest {

    public static void main(String[] args) {

        String str = "abc|cde|asa|dasdsa";
        List<String> strlist = StringUtil.splitStr2List(str, "\\|");
        for (String s : strlist)
            System.out.println(s);

        System.out.println(StringUtil.connectStr(strlist, "|"));

    }
}
