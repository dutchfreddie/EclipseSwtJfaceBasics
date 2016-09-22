package httpbotexamples.chapter3;

import httpbotexamples.util.FileIOUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadingAndWritingHTMLPages {

	public static void main(String[] args) {
		
		String urlString = "http://localhost/text.html";
		String fileTo="./text.html";
		URL url;
		try {
			url = new URL(urlString);
			
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(fileTo);
			FileIOUtil.saveBinaryOrTextURL(is, os, true);
			System.out.println(FileIOUtil.returnHTMLPage(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
