package httpbotexamples.chapter5;

import java.io.*;
import java.net.*;

import javax.net.ssl.*;


public class IsHTTPS{
	public static void main(String args[]){
		//String strURL = "https://blu172.mail.live.com/default.aspx";
		String strURL ="http://www.condast.com/condast/index.php";
		URL url;
		try{
			url = new URL(strURL);
			URLConnection conn = url.openConnection();
			conn.connect();
			if (conn instanceof HttpsURLConnection){
				System.out.println("Valid HTTPS URL");
				HttpsURLConnection https = (HttpsURLConnection) conn;
				System.out.println(https.getCipherSuite());
				
			}
			else if (conn instanceof HttpURLConnection){
				System.out.println("Valid HTTP URL");
			} 
			else
			{
				System.out.println("Unknown protocol URL");
			}
		} catch (MalformedURLException e){
			System.out.println("Invalid URL");
		} catch (IOException e){
			System.out.println("Error connecting to URL");
		}
	}
}
