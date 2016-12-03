package com.rc.portal.webapp.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * Created by ws on 2015/4/13.
 */
public class ParametersCommon {

    private static final Logger log = Logger.getLogger(ParametersCommon.class);

    public static Map<String,String> getParams(  HttpServletRequest request )
    {
        return getParams( request, "" );
    }

    @SuppressWarnings("rawtypes")
	public static Map<String,String> getParams(  HttpServletRequest request, String input_charset )
    {
        Map<String,String> params = new HashMap<String,String>();
        try {
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); )
            {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++)
                {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                if( !StringUtil.isNull(input_charset) ) {
                    valueStr = new String(valueStr.getBytes("ISO-8859-1"), input_charset );
                }
                log.info( "valueStr:" + name + ":" + valueStr );
                params.put(name, valueStr);
            }
        }catch ( Exception e ){
            log.error( e );
        }
        return params;
    }

    public static String getWXSign(Map<String,String> map, String key )
    {
        return getSign( map, key , "&key=", true );
    }
    public static String getSign(Map<String,String> map, String key )
    {
        return getSign( map, key , "", false );
    }
    public static String getSign(Map<String,String> map, String key, boolean toUpperCase )
    {
        return getSign( map, key , "", toUpperCase );
    }
    public static String getSign(Map<String,String> map, String key, String keyPrefix, boolean toUpperCase )
    {

        String result = getOriginalString( map, key, keyPrefix, toUpperCase );
        log.debug("original:" + result);
        result = DigestUtils.md5Hex(result);

        if( toUpperCase )
        {
            result = result.toUpperCase();
        }

        return result;
    }

    public static String getSHA1Sign( Map<String,String> map )
    {
        return getSHA1Sign( map, "", "", false );
    }
    public static String getSHA1Sign(Map<String,String> map, String key, String keyPrefix, boolean toUpperCase )
    {

        String result = getOriginalString( map, key, keyPrefix, toUpperCase );
        log.debug("sha1 original:" + result);
        result = DigestUtils.shaHex(result);

        if( toUpperCase )
        {
            result = result.toUpperCase();
        }

        return result;
    }

    public static String getOriginalString( Map<String,String> map, String key, String keyPrefix, boolean toUpperCase )
    {
        ArrayList<String> list = new ArrayList<String>();

        for( Map.Entry<String,String> entry : map.entrySet() )
        {
            if( "".equals( entry.getValue() )  )
            {
                continue;
            }
            String item = "";
            item += entry.getKey() + "=" + entry.getValue();
            list.add( item );
        }

        int size = list.size();
        String [] arrayToSort = list.toArray( new String[ size ] );

        Arrays.sort( arrayToSort, String.CASE_INSENSITIVE_ORDER );

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++)
        {
            if( i > 0 )
            {
                sb.append( "&" );
            }
            sb.append(arrayToSort[i]);
        }
        sb.append(keyPrefix);
        sb.append(key);
        String result = sb.toString();

        return result;
    }

    public static String getOriginalString(String timestamp, String nonce, String token)
    {
        String[] arr = new String[] { token, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        return content.toString();

    }


}
