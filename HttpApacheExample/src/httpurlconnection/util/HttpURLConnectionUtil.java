package httpurlconnection.util;


import httpapacheexample.util.general.FileIOUtil;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class HttpURLConnectionUtil {
	
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static URL createURL(String url) throws MalformedURLException{
		return new URL(url);
	}
	
	public static HttpURLConnection createURLConnection(URL url) throws IOException{		
		return (HttpURLConnection) url.openConnection();
	}
	
	public static InputStream getInputStream(HttpURLConnection httpURLConnection) throws IOException{
		return httpURLConnection.getInputStream();
	}
	
	public static Map<String,List<String>> getHeaderFields(HttpURLConnection conn){
		
		return conn.getHeaderFields();
	}
	
	public static boolean isContentTypeHeaderText(HttpURLConnection httpURLConnection){
		
		String type = httpURLConnection.getHeaderField("Content-Type").toLowerCase().trim();
		
		return (type=="text") ? true : false;
	}
	
	public static int getResponseCode(HttpURLConnection con) throws IOException{
		return con.getResponseCode();
	}	
	
	public static void printoutInfoURL(URL url){
		String authority = url.getAuthority();//String
		int defaultPort = url.getDefaultPort();//int
		String file = url.getFile();//String
		String host = url.getHost();//String;
		String path = url.getPath();//String
		String protocol = url.getProtocol();//String
		String query = url.getQuery();//String
		String ref = url.getRef();//String
		String userinfo = url.getUserInfo();//String
		Map<String,String> map = new TreeMap<>();
		map.put("authority", authority);
		map.put("default port", String.valueOf(defaultPort));
		map.put("file", file);
		map.put("host", host);
		map.put("path", path);
		map.put("protocol", protocol);
		map.put("query", query);
		map.put("ref", ref);
		map.put("userinfo", userinfo);
		System.out.println("**Info URL**");
		for(Entry<String,String> entry:map.entrySet()){
			System.out.format("%-15s=%s\n", entry.getKey(),entry.getValue());
		}
		System.out.println("**End info URL**");
	}
	
	public static void printoutInfoHttpURLConnection(HttpURLConnection conn){
		
	}
	
	public static HttpURLConnection setRequestPropertiesPost(HttpURLConnection con,String cookies) throws IOException{
		
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Cookie", cookies);
		
		String EMAIL= "email";
		String PASS = "pass";
		String username = "frederik_rotsaert@hotmail.com";
		String password = "ZeeltCondast20";
		String urlParameters = EMAIL + "=" + username + "&" + PASS + "=" + password;
		 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();		
		
		return con;				
	}
	
	public static String getCookieList(Map<String,List<String>> headerFields){
		Set<String> headerFieldsSet = headerFields.keySet();
		Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
		 String cookies = "";
		while (hearerFieldsIter.hasNext()) {
			String headerFieldKey = hearerFieldsIter.next();
            
            if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)){
            	List<String> headerFieldValue = headerFields.get(headerFieldKey);
            	for (String headerValue : headerFieldValue) {          		
                     
                     String[] fields = headerValue.split(";\\s*"); 
                     String cookieValue = fields[0];
                     cookies += cookieValue + "; ";
            	}
            }
		}
		
		return cookies.trim();
	}
	
	public static String printoutCookiesFullInfo(Map<String,List<String>> headerFields){
		
		Set<String> headerFieldsSet = headerFields.keySet();
		Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
		 String cookies = "";
		while (hearerFieldsIter.hasNext()) {
         
             String headerFieldKey = hearerFieldsIter.next();
             
             if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)){
            	 List<String> headerFieldValue = headerFields.get(headerFieldKey);
            	 
            	 for (String headerValue : headerFieldValue) {
                      
            		 System.out.println("Cookie Found...");
                     
                     String[] fields = headerValue.split(";\\s*");
  
                     String cookieValue = fields[0];
                     cookies += cookieValue + "; ";
                     String expires = null;
                     String path = null;
                     String domain = null;
                     boolean secure = false;
                      
                     // Parse each field
                     for (int j = 1; j < fields.length; j++) {
                         if ("secure".equalsIgnoreCase(fields[j])) {
                             secure = true;
                         }
                         else if (fields[j].indexOf('=') > 0) {
                             String[] f = fields[j].split("=");
                             if ("expires".equalsIgnoreCase(f[0])) {
                                 expires = f[1];
                             }
                             else if ("domain".equalsIgnoreCase(f[0])) {
                                 domain = f[1];
                             }
                             else if ("path".equalsIgnoreCase(f[0])) {
                                 path = f[1];
                             }
                         }
                         
                    }
                     
                    System.out.println("cookieValue:" + cookieValue);
                    System.out.println("expires:" + expires);
                    System.out.println("path:" + path);
                    System.out.println("domain:" + domain);
                    System.out.println("secure:" + secure);
                      
                    System.out.println("*****************************************");
                 
                 }
                  
             }
            }	
		
		return cookies.trim();
	}

	
	 public static void saveHttpURLConnection(HttpURLConnection httpURLConnection,String outputFile) throws IOException{
		 InputStream is = httpURLConnection.getInputStream();
		    OutputStream os = new FileOutputStream(outputFile);
		    String type = httpURLConnection.getHeaderField("Content-Type").toLowerCase().trim();
		    if (type.startsWith("text"))
		    	FileIOUtil.saveBinaryOrTextURL(is, os, true);
		    else
		      FileIOUtil.saveBinaryOrTextURL(is, os, false);
		    is.close();
		    os.close();
		    httpURLConnection.disconnect();
	 }
	 
	 
}
