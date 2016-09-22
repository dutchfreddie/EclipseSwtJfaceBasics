package httpbotexamples.chapter4;

import httpbotexamples.util.FileIOUtil;
import httpbotexamples.util.HttpURLConnectionUtil;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

public class HttpURLConnectionExample {

	public static void main(String[] args) {
		
		String httpURL = "https://api.linkedin.com/v1/";
		String dir = "headerHttp";	
		String sub = "linkedin_api";
		//String prefix = dir+ "_"+ sub + "_";
		String fullDir = "G://JavaWorkSpace//Workspace-HTTP//WorkspaceHTTPBot//" + dir;
		
		
		HttpURLConnectionExample example = new HttpURLConnectionExample();
		
		example.createDirectory(fullDir);
		
		String outputFileDir = fullDir + "//";
		//String fileTot =  prefix + "tot.html";
		//String fileBody = prefix + "body.html";
		//String fileFormPart = prefix + "form.html";
		String fileHttpHeader = outputFileDir + "httpheaders_" + sub + ".txt";
		
		//String outputFileTot = outputFileDir+fileTot;
		//String outputFileBody = outputFileDir+fileBody;
		//String outputFileForm = outputFileDir + fileFormPart;
		//String outputFileHttpHeader = outputFileDir + fileHttpHeader;
		try {
			URL url = new URL(httpURL);
			
			HttpURLConnection http = example.getHttpURLConnection(url);
			http.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

			example.printoutHeader(http,fileHttpHeader);
			//example.saveURLToFile(http, outputFileTot);
			//example.saveURLBodyToFile(outputFileTot, outputFileBody);
			//example.saveCssSelectorToFile(outputFileBody, outputFileForm, "form");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void createDirectory(String dir){
		File file = new File(dir);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
	}
	
	private void printoutHeader(HttpURLConnection http,String file) throws IOException{
		
		String request = HttpURLConnectionUtil.scanRequestHeader(http);
	    String response = HttpURLConnectionUtil.scanResponseHeaders(http);
	    String tot = request + "\n\n" + response;
	    FileIOUtil.writeStringToFile(tot, file);
	}
	
	private void saveURLToFile(HttpURLConnection httpURLConnection,String outputFile) throws IOException{
		HttpURLConnectionUtil.saveHttpURLConnection(httpURLConnection, outputFile);
	}
	
	private void saveURLBodyToFile(String outputFileURLTot,String outputFileURLBody) throws IOException{
		File in = new File(outputFileURLTot);
		Document doc = Jsoup.parse(in, "UTF-8");
		String body = doc.select("body").html();
		FileIOUtil.writeStringToFile(body, outputFileURLBody);
	}
	
	private void saveCssSelectorToFile(String inFile,String outFile,String selector) throws IOException{
		File in = new File(inFile);
		StringBuffer sb = new StringBuffer();
		Document doc = Jsoup.parse(in, "UTF-8");
		Elements elements = doc.select(selector);
		List<FormElement> forms = elements.forms();
		
		sb.append("Form-elements:\n");
		int x = 1;
		for(FormElement f:forms){
			sb.append(x + ":\n");
			sb.append(f.html());
			sb.append("\n");
			x++;
		}
		sb.append("*****************\n\n\n");
		
		
		x = 1;
		sb.append("Collection Elements:\n");
		for(Element element:elements){
			sb.append(x + ":\n");
			Attributes attrs = element.attributes();
			sb.append("Collection-Attributes:\n");
			for(Attribute at:attrs){
				sb.append(at.getKey() + " = " + at.getValue() + "\n");
			}
			sb.append("html-attributes-collection:\n");
			sb.append(attrs.html());
			sb.append("\n\n");
			sb.append("html-element:\n");
			sb.append(element.html());
			sb.append("\n*******************\n");
			x++;
		}
		sb.append("\n\n");
		sb.append("doc.select(form).html:\n");
		String body = doc.select(selector).html();
		sb.append(body);
		
		FileIOUtil.writeStringToFile(sb.toString(), outFile);
	}
	
	
	
	private HttpURLConnection getHttpURLConnection(URL url) throws IOException{
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		return http;
	}
	
	
	    
	  

}
