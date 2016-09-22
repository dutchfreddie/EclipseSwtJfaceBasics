package httpapacheexample.util;

import httpapacheexample.util.general.FileIOUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpClientUtil {
	
	public static HttpClient createHttpClient(){
		
		HttpClient httpclient = HttpClients.createDefault();
		
		return httpclient;
    }
	
	public static HttpGet createHttpGet(HttpClient httpclient,String url){
		
		HttpGet request = new HttpGet(url);
		
		return request;
	}
	
	public static HttpResponse createHttpResponse(HttpClient client,HttpGet request) throws ClientProtocolException, IOException{
		
		HttpResponse response = client.execute(request);
		
		return response;
	}
	
	public static void getHttpResponseHeader(HttpResponse response){
		Header[] headerArray = response.getAllHeaders();
		for(int i = 0;i<headerArray.length;i++){
			System.out.println(headerArray[i].getName() + "" + headerArray[i].getValue());
		}
	}
	
	public static void returnHeadersHttpResponse(HttpResponse httpget){
		StringBuffer sb = new StringBuffer();
		Header[] headers = httpget.getAllHeaders();
		for(Header h:headers){
			System.out.println(h.getName() + " = " + h.getValue());
			HeaderElement[] helements = h.getElements();
			for(HeaderElement he:helements){
				NameValuePair[] nvps = he.getParameters();
				for(NameValuePair nvp:nvps){
					System.out.println(nvp.getName() + " = " + nvp.getValue());
				}
			}
		}		
	}
	
	public static HttpEntity getHttpEntity(HttpResponse response){
		return response.getEntity();
	}
	
	public static void saveHTMLtoFile(HttpEntity httpEntity,String fileTo) throws IllegalStateException, IOException{
		
		StringBuffer sb = new StringBuffer();
		InputStream in = httpEntity.getContent();
		
		BufferedReader rd = new BufferedReader (new InputStreamReader(in));
		String line = "";
		while ((line = rd.readLine()) != null) {
			  sb.append(line);
		} 
		
		FileIOUtil.writeStringToFile(sb.toString(), fileTo);
	}
	
	public static void saveURLBodyToFile(String outputFileURLTot,String outputFileURLBody) throws IOException{
		File in = new File(outputFileURLTot);
		Document doc = Jsoup.parse(in, "UTF-8");
		String body = doc.select("body").html();
		FileIOUtil.writeStringToFile(body, outputFileURLBody);
	}	
}
