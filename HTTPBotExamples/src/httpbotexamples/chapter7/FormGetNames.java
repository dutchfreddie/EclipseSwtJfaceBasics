package httpbotexamples.chapter7;

import httpbotexamples.util.FormUtilNonMulitPart;
import httpbotexamples.util.FormUtility;
import httpbotexamples.util.ParseHTML;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class FormGetNames {
	
	public static void main(String args[]) throws IOException{
		
		String startURL = "http://localhost/phpexample/login.php";
		Document doc = Jsoup.connect(startURL).get();
		Element form = doc.select("form").first();
		for(Element el:form.getAllElements()){
			System.out.println(el.html());
		}
		String toURL="";
		String method = "";
		Attributes att = form.attributes();
		List<Attribute> list = att.asList();
		for(Attribute at:list){
			System.out.println(at.getKey() + " " + at.getValue());
			if(at.getKey().equals("action")){
				toURL = at.getValue();
			}
			if(at.getKey().equals("method")){
				method = at.getValue().toLowerCase();
			}
		}
		
		String firstname = "Frederik";
		String lastname = "Rotsaert";
		
		if(method.equals("get")){
			String toURLtot = returnGETURL(toURL+"?",firstname,lastname);
			System.out.println(toURLtot);
			Document docTo = Jsoup.connect(toURLtot).get();
			String docToS=docTo.html();
			System.out.println(docToS);
		}else{
			 	URL url = new URL(toURL);
			    URLConnection http = url.openConnection();
			    http.setDoOutput(true);
			    OutputStream os = http.getOutputStream();
			    
			    FormUtility form2 = new FormUtility(os, null);
			    form2.add("firstname", firstname);
			    form2.add("lastname", lastname);			    
			    //form2.complete();

			    // read the results
			    InputStream is = http.getInputStream();
			    Document d = Jsoup.parse(is,null,"");
			    System.out.println(d.html());			    
			    
		}
		
		
		
	}
	
	private static String returnGETURL(String startURL,String firstname,String lastname) throws IOException{
		// build the URL
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    FormUtilNonMulitPart form = new FormUtilNonMulitPart(bos, null);
	    form.add("firstname", firstname);
	    form.add("lastname", lastname);	   

	    String totURL = startURL + bos.toString();
	    
	    return totURL;
	}
   

}
