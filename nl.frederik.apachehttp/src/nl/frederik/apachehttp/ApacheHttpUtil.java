package nl.frederik.apachehttp;

import java.net.URI;
import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

public class ApacheHttpUtil {

	public static void getHttpGetInfo(HttpGet client){
		
		getHeadersProtocolVersion(client);
		RequestLine requestLine = client.getRequestLine();
		String methodR = requestLine.getMethod();
		String uriR = requestLine.getUri();
		String method = client.getMethod();
		URI uri = client.getURI();
		RequestConfig config = client.getConfig();
		if(config!=null){
			config.getConnectionRequestTimeout();
			config.getConnectTimeout();
			config.getCookieSpec();
			config.getLocalAddress();
			config.getMaxRedirects();
			config.getProxy();
			config.getSocketTimeout();	
		}
		
		
	}
	
	public static void getHttpResponseInfo(HttpResponse response){
		getHeadersProtocolVersionResponse(response);
		
		Locale locale = response.getLocale();
		String country = locale.getCountry();
		String language = locale.getLanguage();
		
		StatusLine statusLine = response.getStatusLine();
		String reasonPhrase = statusLine.getReasonPhrase();
		int statusCode = statusLine.getStatusCode();
	}
	
	public static void getHttpEntityInfo(HttpEntity entity){
		Header header = entity.getContentEncoding();
		if(header!=null){
			String headerName = header.getName();
			String headerValue = header.getValue();	
		}		
				
		long contentLength = entity.getContentLength();
		
		Header contentType = entity.getContentType();
		if(contentType!=null){
			String ctName = contentType.getName();
			String ctValue = contentType.getValue();	
		}		
	}
	
	public static void getHttpPostInfo(HttpPost client){
		getHeadersProtocolVersion(client);
		RequestLine requestLine = client.getRequestLine();
		String methodR = requestLine.getMethod();
		String uriR = requestLine.getUri();
		String method = client.getMethod();
		URI uri = client.getURI();
		RequestConfig config = client.getConfig();
		if(config!=null){
			config.getConnectionRequestTimeout();
			config.getConnectTimeout();
			config.getCookieSpec();
			config.getLocalAddress();
			config.getMaxRedirects();
			config.getProxy();
			config.getSocketTimeout();	
		}
	}
	
	
	
	private static void getHeadersProtocolVersion(HttpGet client){
		Header[] headers = client.getAllHeaders();
		for(Header header:headers){
			String name = header.getName();
			String value = header.getValue();
			HeaderElement[] elements = header.getElements();
			for(HeaderElement element:elements){
				String hName = element.getName();
				String hValue =element.getValue();
			}
		}
		ProtocolVersion version = client.getProtocolVersion();
		String protocol = version.getProtocol();
		int minor = version.getMinor();
		int major = version.getMajor();
	}
	
	private static void getHeadersProtocolVersion(HttpPost client){
		Header[] headers = client.getAllHeaders();
		for(Header header:headers){
			String name = header.getName();
			String value = header.getValue();
			HeaderElement[] elements = header.getElements();
			for(HeaderElement element:elements){
				String hName = element.getName();
				String hValue =element.getValue();
			}
		}
		ProtocolVersion version = client.getProtocolVersion();
		String protocol = version.getProtocol();
		int minor = version.getMinor();
		int major = version.getMajor();
	}
	
	private static void getHeadersProtocolVersionResponse(HttpResponse client){
		Header[] headers = client.getAllHeaders();
		for(Header header:headers){
			String name = header.getName();
			String value = header.getValue();
			HeaderElement[] elements = header.getElements();
			for(HeaderElement element:elements){
				String hName = element.getName();
				String hValue =element.getValue();
			}
		}
		ProtocolVersion version = client.getProtocolVersion();
		String protocol = version.getProtocol();
		int minor = version.getMinor();
		int major = version.getMajor();
	}
}
