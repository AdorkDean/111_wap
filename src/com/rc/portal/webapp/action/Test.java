package com.rc.portal.webapp.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public static String convertStreamToString(InputStream is) {   

		   BufferedReader reader = new BufferedReader(new InputStreamReader(is));   

		        StringBuilder sb = new StringBuilder();   

		    

		        String line = null;   

		        try {   

		            while ((line = reader.readLine()) != null) {   

		                sb.append(line + "/n");   

		            }   

		        } catch (IOException e) {   

		            e.printStackTrace();   

		        } finally {   

		            try {   

		                is.close();   

		            } catch (IOException e) {   

		                e.printStackTrace();   

		            }   

		        }   

		    

		        return sb.toString();   

		    }   
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		 String url ="https://oauth.jd.com/oauth/token?grant_type=authorization_code&client_id=1A06D81EC4DA8E60694CD0A153D16FF1&client_secret=46ba757831074f449b31e4dd238e5cf6&scope=read&redirect_uri=http://www.jkr99.com&code=VD6zHt&state=111";
//		 &redirect_uri=http://www.jkr99.com"
//                 +"&code=Cf5Dy8&state=0";
		 URL uri = new URL(url);
		HttpURLConnection conn =(HttpURLConnection) uri.openConnection();
		conn.setRequestProperty("Accept-Charset","utf-8");
		conn.setRequestMethod("POST");
		int code = conn.getResponseCode();
		InputStream is =conn.getInputStream();
		String i = convertStreamToString(is);
		System.out.println(i);
	}

}
