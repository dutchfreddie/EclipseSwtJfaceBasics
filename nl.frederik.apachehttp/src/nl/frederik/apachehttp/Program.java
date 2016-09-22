package nl.frederik.apachehttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class Program {

	public static void main(String[] args) {
		
		HttpClient client = HttpClients.createDefault();
		String url  = "http://www.nrc.nl/";
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("param1", "value1"));
		formparams.add(new BasicNameValuePair("param2", "value2"));
		UrlEncodedFormEntity  urlFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(urlFormEntity);
		ApacheHttpUtil.getHttpPostInfo(httpPost);
		
		HttpResponse response=null;
		try {
			response = client.execute(httpPost);
			ApacheHttpUtil.getHttpResponseInfo(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(response!=null&&response.getStatusLine().getStatusCode()==200){
			HttpEntity entity = response.getEntity();
			ApacheHttpUtil.getHttpEntityInfo(entity);
			StringBuffer buffer = new StringBuffer();
			if (entity != null) {
					InputStream instream=null;
					try {
						instream = entity.getContent();
						BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
						String line = null;
						while ((line = rd.readLine()) != null) {
							buffer.append( line );
						}	
					} catch (UnsupportedOperationException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			System.out.println(buffer.toString());
		}
		
	}
	
	private static void httpGetExample(){
		
		HttpClient client = HttpClients.createDefault();
		String url  = "http://www.nrc.nl/";
		
		HttpGet httpGet = new HttpGet(url);
		ApacheHttpUtil.getHttpGetInfo(httpGet);
		
		HttpResponse response=null;
		try {
			response = client.execute(httpGet);
			ApacheHttpUtil.getHttpResponseInfo(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(response!=null&&response.getStatusLine().getStatusCode()==200){
			HttpEntity entity = response.getEntity();
			ApacheHttpUtil.getHttpEntityInfo(entity);
			StringBuffer buffer = new StringBuffer();
			if (entity != null) {
					InputStream instream=null;
					try {
						instream = entity.getContent();
						BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
						String line = null;
						while ((line = rd.readLine()) != null) {
							buffer.append( line );
						}	
					} catch (UnsupportedOperationException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			System.out.println(buffer.toString());
		}
	}

}
