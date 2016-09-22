package httpurlconnection.util;

import httpapacheexample.util.HttpClientUtil;
import httpapacheexample.util.general.FileIOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpURLConnectionExample {

	public static void main(String[] args) {
		String urlGet = "https://www.facebook.com";
		String outputDir = "C://Apache24//htdocs//HttpURLConnectionExample//";
		String outputFile = outputDir + "facebookLogin_get.html";
		try {
			get(urlGet,outputFile);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	private static void get(String urlS,String fileTo) throws IOException{
		URL url = HttpURLConnectionUtil.createURL(urlS);
		HttpURLConnectionUtil.printoutInfoURL(url);
		HttpURLConnection conn = HttpURLConnectionUtil.createURLConnection(url);
		Map<String,List<String>> list = HttpURLConnectionUtil.getHeaderFields(conn);
		String cookies = HttpURLConnectionUtil.printoutCookiesFullInfo(list);
		System.out.println("**" + cookies + "**");	
		InputStream inputStream = HttpURLConnectionUtil.getInputStream(conn);
		boolean isText = HttpURLConnectionUtil.isContentTypeHeaderText(conn);
		if(isText){
			FileIOUtil.saveTextURLtoFile(inputStream, fileTo);
		}		
	}
	
	private static void getPost(){
		String urlGet = "https://www.facebook.com";
		String cookies = "";
		try {
			URL url = HttpURLConnectionUtil.createURL(urlGet);
			HttpURLConnection conn = HttpURLConnectionUtil.createURLConnection(url);
			Map<String,List<String>> list = HttpURLConnectionUtil.getHeaderFields(conn);
			cookies = HttpURLConnectionUtil.printoutCookiesFullInfo(list);
			System.out.println("**" + cookies + "**");
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String urlS = "https://www.facebook.com//login.php?login_attempt=1";
		try {
			URL url = HttpURLConnectionUtil.createURL(urlS);
			URLConnection con = HttpURLConnectionUtil.createURLConnection(url);
			con = HttpURLConnectionUtil.setRequestPropertiesPost((HttpURLConnection) con,cookies);
			System.out.println(HttpURLConnectionUtil.getResponseCode((HttpURLConnection) con));
			
			String outputDir = "C://Apache24//htdocs//HttpURLConnectionExample//";
			String outputFilePost = outputDir + "facebookLogin_post2.html";
			String outputFilePostBody = outputDir + "facebookLogin_post2_body.html";
			HttpURLConnectionUtil.saveHttpURLConnection((HttpURLConnection) con, outputFilePost);
			HttpClientUtil.saveURLBodyToFile(outputFilePost, outputFilePostBody);
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
