package httpapacheexample.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



public class CloseableHttpClientUtil {

	
	private final static String USER_AGENT = "Mozilla/5.0";
/**
 * Create an CloseableHttpClient with HttpClientBuilder.create().build
*/
	public static CloseableHttpClient createCloseableHttpClientWithBuild(){
		
			CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		
		return httpclient;
    }

	/**
	 * Create a closeableHttpClient with HttpClients factory, default
	 * @return
	 */
	public static CloseableHttpClient createCloseableHttpClientWithFactory(){
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		return httpclient;
    }
	
	/**
	 * Create HttpGet
	 * @param String url
	 * @return HttpGet
	 */
	public static HttpGet createHttpGet(String url){
		
		HttpGet request = new HttpGet(url);
		
		return request;
	}
	
	/**
	 * 
	 * @param CloseableHttpClient client
	 * @param HttpRequestBase (base for HttpGet,Post) request
	 * @return CloseableHttpResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static CloseableHttpResponse createHttpResponse(CloseableHttpClient client,HttpRequestBase request) throws ClientProtocolException, IOException{
		
		CloseableHttpResponse response = client.execute(request);
		
		return response;
	}
	
	public static HttpEntity createHttpEntity(CloseableHttpResponse response){
		
		return response.getEntity();
	}
	
	public static void printoutHttpResponseHeader(HttpResponse response){
		System.out.println("**HttpResponseHeaderArray-Name=Value**");
		Header[] headerArray = response.getAllHeaders();
		for(int i = 0;i<headerArray.length;i++){
			System.out.format("%-20s = %s\n", headerArray[i].getName(),headerArray[i].getValue());
		}
		System.out.println();
	}
	
	public static void printoutHttpResponseHeadersFull(HttpResponse response){
		System.out.println("**Full HttpResponseHeaderArray-Name=Value**");
		Header[] headers = response.getAllHeaders();
		for(Header h:headers){
			System.out.println("**Name=Value**");
			System.out.format("%-20s = %s\n", h.getName(),h.getValue());
			HeaderElement[] helements = h.getElements();
			for(HeaderElement he:helements){			
				System.out.println("**HeaderElements-Name-Value**");
				NameValuePair[] nvps = he.getParameters();
				for(NameValuePair nvp:nvps){
					System.out.format("%-20s = %s\n", nvp.getName(),nvp.getValue());
				}
				System.out.println("****\n");
			}
			System.out.println("****");
		}
		System.out.println();
	}
	
	public static InputStream getInputStreamHttpEntity(HttpEntity entity) throws IOException{
		return entity.getContent();
	}
	
	public static void getEntityInfo(HttpEntity entity){
		Header contentEncoding = entity.getContentEncoding();//Header
		entity.getContentLength();//long
		entity.getContentType();//Header
	}
	
	public static BasicCookieStore createBasicCookStore(){
		 return new BasicCookieStore();
	 }
	
	
	/**
	 * Create CloseableHttpClientWithBasicCookstore
	 * @return
	 */
	 public static CloseableHttpClient createHttpClientWithBasicCookieStore(BasicCookieStore cookieStore){
	    	//BasicCookieStore cookieStore = new BasicCookieStore();
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore)
	                .build();
	        
	        return httpclient;
	 }
	 
	 public static void printOutCookieStore(BasicCookieStore cookieStore){
		 
		 List<Cookie> cookies = cookieStore.getCookies();
         if (cookies.isEmpty()) {
             System.out.println("None");
         } else {
             for (int i = 0; i < cookies.size(); i++) {
            	 Cookie c = cookies.get(i);
            	 System.out.format("%-20s = %s\n", c.getName(),c.getValue());
                 System.out.println("- " + cookies.get(i).toString());
             }
         }
	 }
	 
	public static String getCookiesAsString(HttpResponse response){
		
		String rs = response.getFirstHeader("Set-Cookie") == null ? "" : 
            response.getFirstHeader("Set-Cookie").toString();
		
		return rs;
	}
	 
	public static void getAllHeaders(HttpResponse response){
		Header[] headerArray = response.getAllHeaders();
		for(int i = 0;i<headerArray.length;i++){
			System.out.println(headerArray[i].getName() + "=" + headerArray[i].getValue());
		}
	}
	
	public static String getCookiesAsOneString(HttpResponse response){
		Header[] headerArray = response.getAllHeaders();
		String rt = "";
		for(int i = 0;i<headerArray.length;i++){
			if(headerArray[i].getName().equals("Set-Cookie")){
				String[] fields = headerArray[i].getValue().split(";\\s*");
				rt += fields[0] +"; ";
			}
		}
		
		return rt.trim();			
	}
	
	 
	
	
	/**
	 *Close HttpResponse
	 * @param CloseableHttp response
	 * @throws IOException
	 */
	public static void closeHttpResponse(CloseableHttpResponse response) throws IOException{
		response.close();
	}
	
	/**
	 * close CloseableHttpClient
	 * @param CloseableHttpClient client
	 * @throws IOException
	 */
	public static void closeCloseableHttpClient(CloseableHttpClient client) throws IOException{
		
		client.close();
	}
	
	/**
	 * Read a small HttpEntity (entity.getContentLenghth<2048)
	 * @param HttpEntity entity
	 * @return String
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String readEntityWithEntityUtilsString(HttpEntity entity) throws ParseException, IOException{
		long len = entity.getContentLength();
		String read = "";
		if (len != -1 && len < 2048) {
			read = EntityUtils.toString(entity);
		} else {
		// Stream content out
		}
		
		return read;		
	}
	
	/**
	 * create List<NameValuePair> for post
	 * @param Map<String,String> map
	 * @return List<NameValuePair> list
	 */
	public static List<NameValuePair> returnFormParms(Map<String,String> map){
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		
		for(Entry<String,String> mp:map.entrySet()){
			BasicNameValuePair nvp = new BasicNameValuePair(mp.getKey(),mp.getValue());
			formparams.add(nvp);
			
		}
		
		return formparams;
	}
	
	/**
	 * 
	 * @param String url
	 * @param List<NameValuePair> listNvp
	 * @return HttpPost post
	 * @throws UnsupportedEncodingException
	 */
	public static HttpPost createHttpPost(String url,List<NameValuePair> listNvp,String cookiesString) throws UnsupportedEncodingException{
		
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Cookie",cookiesString);
		
		
		post.setEntity(new UrlEncodedFormEntity(listNvp));
		
		return post;
		
	}
	
	
}
