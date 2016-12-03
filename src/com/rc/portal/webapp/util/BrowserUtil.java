package com.rc.portal.webapp.util;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.rc.portal.util.StringSearchHelper;
import com.rc.portal.util.StringUtil;

/**
 * Created by ws on 2015/10/21.
 */
public class BrowserUtil {

    private Logger log = Logger.getLogger( BrowserUtil.class );

    public static boolean isMicroMessengerBrowser( HttpServletRequest httpServletRequest )
    {
        String user_agent = httpServletRequest.getHeader("user-agent" );

        return isMicroMessengerBrowser( user_agent );

    }

    public static boolean isMicroMessengerBrowser( String user_agent )
    {
        return StringSearchHelper.find( user_agent, "MicroMessenger" );
    }

    public static Map<String, String> getRequestHeaders( HttpServletRequest httpServletRequest )
    {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        if( headerNames != null )
        {
            while ( headerNames.hasMoreElements() )
            {
                String headName = (String)headerNames.nextElement();
                Enumeration<String> headers = httpServletRequest.getHeaders(headName);
                StringBuffer stringBuffer = new StringBuffer("");
                if( headers != null )
                {
                    while ( headers.hasMoreElements() )
                    {
                        if( !StringUtil.isNull( stringBuffer.toString() ) && StringUtil.isEmpty( stringBuffer.toString() ) )
                        {
                            stringBuffer.append("|");
                        }
                        String header = headers.nextElement();
                        stringBuffer.append(header);
                    }
                }

                map.put( headName, stringBuffer.toString() );

            }
        }

        return map;

    }

}
