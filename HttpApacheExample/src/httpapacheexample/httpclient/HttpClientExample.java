package httpapacheexample.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import httpapacheexample.util.CloseableHttpClientUtil;
import httpapacheexample.util.HttpClientUtil;
import httpapacheexample.util.general.FileIOUtil;
import httpapacheexample.util.general.JsoupParsingUtil;

public class HttpClientExample {

	public static void main(String[] args) {
		
		String url = "https://www.facebook.com";
		String outputDir = "C://Apache24//htdocs//CloseableHttpClientExample//";
		String outputFile = outputDir + "facebookLogin_get.html";
		String tag = "div";
		String outputFileTag = outputDir + "facebookLogin_get_" +tag +".html";
		
			
		HttpClientExample de = new HttpClientExample();
		try {
			//de.simpleCloseableExample(url,outputFile,outputFileTag,tag);
			de.methodForCloseableHttpClientWithCookie(url, outputFile, outputFileTag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void simpleCloseableExample(String url,String fileTo,String fileToTag,String tag) throws ClientProtocolException, IOException{
		//CloseableHttpClient httpclient = CloseableHttpClientUtil.createCloseableHttpClientWithBuild();
		//HttpGet httpGet = CloseableHttpClientUtil.createHttpGet(url);
		//CloseableHttpResponse response = CloseableHttpClientUtil.createHttpResponse((CloseableHttpClient) httpclient, httpGet);
		//CloseableHttpClientUtil.printoutHttpResponseHeadersFull(response);
		//CloseableHttpClientUtil.printoutHttpResponseHeader(response);
		//System.out.println(CloseableHttpClientUtil.getCookiesAsOneString(response));
		//HttpEntity entity = CloseableHttpClientUtil.createHttpEntity(response);
		//InputStream inputStream = CloseableHttpClientUtil.getInputStreamHttpEntity(entity);
		
		//FileIOUtil.saveTextURLtoFile(inputStream, fileTo);
		JsoupParsingUtil.saveTagContentToFile(fileTo, fileToTag, tag);
	
	}
		
	
	private void methodForCloseableHttpClientWithCookie(String url,String fileTo,String fileToBody) throws ClientProtocolException, IOException{
		
		BasicCookieStore cookieStore = CloseableHttpClientUtil.createBasicCookStore();
		HttpClient httpclient = CloseableHttpClientUtil.createHttpClientWithBasicCookieStore(cookieStore);
		HttpGet httpGet = CloseableHttpClientUtil.createHttpGet(url);
		HttpResponse response = CloseableHttpClientUtil.createHttpResponse((CloseableHttpClient) httpclient, httpGet);
		CloseableHttpClientUtil.printOutCookieStore(cookieStore);
		
		/*
		String cookiesAsOneString = CloseableHttpClientUtil.getCookiesAsOneString(response);		
		String EMAIL= "email";
		String PASS = "pass";
		String username = "frederik_rotsaert@hotmail.com";
		String password = "ZeeltCondast20";
		Map<String,String> map = new TreeMap<>();
		map.put(EMAIL, username);
		map.put(PASS, password);
		String urlPost = "https://www.facebook.com//login.php";
		List<NameValuePair> listNvp = CloseableHttpClientUtil.returnFormParms(map);
		HttpPost httpPost = CloseableHttpClientUtil.createHttpPost(urlPost, listNvp,cookiesAsOneString);
		HttpResponse responsePost = CloseableHttpClientUtil.createHttpResponse((CloseableHttpClient) httpclient, httpPost);
		HttpEntity httpEntityPost = HttpClientUtil.getHttpEntity(responsePost);
		
		String outputDir = "C://Apache24//htdocs//HttpURLConnectionExample//";
		String outputFilePost = outputDir + "facebookLogin_post.html";
		String outputFileBodyPost = outputDir + "facebookLogin_post_body.html";
		
		HttpClientUtil.saveHTMLtoFile(httpEntityPost, outputFilePost);	
		HttpClientUtil.saveURLBodyToFile(outputFilePost, outputFileBodyPost);
		CloseableHttpClientUtil.printOutCookieStore(cookieStore);
		
		CloseableHttpClientUtil.closeHttpResponse((CloseableHttpResponse) response);
		CloseableHttpClientUtil.closeHttpResponse((CloseableHttpResponse) responsePost);
		CloseableHttpClientUtil.closeCloseableHttpClient((CloseableHttpClient) httpclient);
		*/
	}

}
