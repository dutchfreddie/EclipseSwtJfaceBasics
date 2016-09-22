package httpapacheexample.defaulthttpclient;

import httpapacheexample.util.DefaultHttpUtil;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;




public class DefaultExample {

	public static void main(String[] args) {
		
		String url = "https://www.facebook.com";
		String outputDir = "C://Apache24//htdocs//HttpURLConnectionExample//";
		String outputFile = "facebookLogin_defaultclient.html";
		String outputFileBody = outputDir + "bodyfacebookLogin_defaultclient.html";
		
		String outputAbsoluteFile = outputDir+outputFile;
		DefaultExample de = new DefaultExample();
		

	}
	
	private void returnHeader(String url) throws IOException {
		HttpClient httpclient = DefaultHttpUtil.createHttpClient(null);
		HttpGet httpget = DefaultHttpUtil.createHttpGet(url);
		DefaultHttpUtil.returnHeaders(httpget);		
	}
	
	

}
