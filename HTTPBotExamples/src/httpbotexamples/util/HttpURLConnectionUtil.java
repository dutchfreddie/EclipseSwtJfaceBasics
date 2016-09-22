package httpbotexamples.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpURLConnectionUtil {

	 public static String scanResponseHeaders(HttpURLConnection httpURLConnection) throws IOException{
		 System.out.println("**Scanning ResponseHeader**");
		 StringBuffer sb = new StringBuffer();
		 sb.append("**Scanning ResponseHeader**\n\n");
		 int count = 0;
		 String key, value;
		 do{
			 key = httpURLConnection.getHeaderFieldKey(count);
			 value = httpURLConnection.getHeaderField(count);
		     count++;
		    
		     if (value != null)
		     {
		    	 key = (key!=null)? key:"No key";
		    	 String s = String.format("%-30s = %-45s\n", key,value);
		    	 sb.append(s);		    	 
			 }		      
		 }while (value != null);
		 		 
		 return sb.toString();
	 }
	 
	 public static String scanRequestHeader(HttpURLConnection httpURLConnection){
		 StringBuffer sb = new StringBuffer();
		 System.out.println("**Scanning RequestHeader**");
		 sb.append("**Scanning RequestHeader**\n\n");
		 Map<String, List<String>> map = httpURLConnection.getRequestProperties();
		 Set<Entry<String,List<String>>> sets = map.entrySet();
		 Iterator<Entry<String,List<String>>> iterator = sets.iterator();
		 if(iterator.hasNext()){
			 Entry<String,List<String>> e = iterator.next();
		    	System.out.println(e.getKey());
		    	for(String s:e.getValue()){
		    		sb.append(e + "\n");
		    		System.out.println(s);
		    	}		    	
		 }
		 System.out.println("**\n");
		 
		 return sb.toString();
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
