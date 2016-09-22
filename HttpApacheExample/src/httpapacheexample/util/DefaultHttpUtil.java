package httpapacheexample.util;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import httpapacheexample.defaulthttpclient.Response;
import httpapacheexample.util.general.FileIOUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultHttpUtil {

	/**
	 * Creates HTTP client. Uses Firefox User-Agent to fool Facebook.
	 * Sets cookies from provided CookieStore.
	 */
	public static DefaultHttpClient createHttpClient(CookieStore cookieStore) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpProtocolParams.setUserAgent(httpclient.getParams(), "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:9.0.1) Gecko/20100101 Firefox/9.0.1");

		if (cookieStore != null) {
			httpclient.setCookieStore(cookieStore);
		}
		return httpclient;
	}

	public static Response get(DefaultHttpClient httpclient,String url, CookieStore cookieStore) throws IOException {
		
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);

		HttpEntity entity = response.getEntity();
		if (entity == null) {
			return null;
		}

		String content = EntityUtils.toString(entity);
		
		//httpclient.getConnectionManager().shutdown();
		return new Response(content, response.getStatusLine(), httpclient.getCookieStore());
	}
	
	public static HttpResponse getHttpResponse(HttpGet httpget,DefaultHttpClient httpclient) throws ClientProtocolException, IOException{
		
		HttpResponse response = httpclient.execute(httpget);
		
		return response;		
	}

	public static HttpGet createHttpGet(String url){
		HttpGet httpget = new HttpGet(url);
		
		return httpget;
	}
	
	public static void returnHeaders(HttpGet httpget){
		//org.eclipse.debug.core.DebugException: com.sun.jdi.ClassNotLoadedException: 
		//Type has not been loaded occurred while retrieving component type of array.
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
