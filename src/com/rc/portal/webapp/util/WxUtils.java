package com.rc.portal.webapp.util;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * 微信分享工具类
 * @author LGP
 * @date 2015年10月26日
 */
public class WxUtils
{
	/**
	 * 生成随机数字符串
	 * @param length 长度
	 * @return 随机字符串
	 */
	public static String getRandomStringByLength(int length) 
	{
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++) 
        {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
	
	/**
	 * 获取当期格式化日期
	 * @return 字符串格式的日期
	 */
	public static String getStringDate()
	{
		Timestamp ta = new Timestamp(new Date().getTime());
		return ta.toString().substring(0, 10);
	}
	
	/**
	 * 获取当期格式化日期
	 * @param date 日期
	 * @return 字符串格式的日期
	 */
	public static String formatDateDate(Date date)
	{
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date).toString().substring(0, 10);
	}
	
	/**
	 * 封装格式数据
	 * @param map
	 * @return
	 */
	public static String getParamSortStrFromMap(Map<String,Object> map)
	{
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet())
        {
            if(entry.getValue()!="")
            {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) 
        {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString().substring(0, sb.length()-1);
        return result;
    }

	/**
	 * Shar1签名
	 * @param str 随机字符串
	 * @return 签名字符串
	 */
	public static String getSha1(String str) 
	{
		if (str == null || str.length() == 0) 
		{
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		try 
		{
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("GBK"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) 
			{
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	
}
