package httpbotexamples.chapter7;

import httpbotexamples.util.FileIOUtil;
import httpbotexamples.util.FormUtilNonMulitPart;
import httpbotexamples.util.FormUtility;
import httpbotexamples.util.HTMLTag;
import httpbotexamples.util.ParseHTML;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FormGETSimple {

	public static void main(String args[]){
		try{
	      FormGETSimple parse = new FormGETSimple();
	      String startURL = "http://localhost/phpexample/simplewelkom.php?";
	      String firstname = "Frederik";
	      String lastname = "Rotsaert";
	      String action = "http://localhost/phpexample/simplewelkom.php";
	      String totaalURL = parse.returnGETURL(startURL, firstname,lastname);
	      System.out.println(totaalURL);
	      
	      URL url = new URL(totaalURL);
	      InputStream is = url.openStream();
	     // OutputStream os = new FileOutputStream("./text.html");
	      //FileIOUtil.saveBinaryOrTextURL(is, os, true);
	      
	      
	      
	      ParseHTML parseHTML = new ParseHTML(is);
	      int count = 0;
	      String beginTag="ul";
	      String endTag="/ul";
	      boolean b = advance(parseHTML,beginTag,count);
	      System.out.println(b);
	      
	     String processString = process(parseHTML,endTag);
	      System.out.println(processString);
	      
	    }catch (Exception e){
	      e.printStackTrace();
	    }
	}
	
	
	
	private String returnGETURL(String startURL,String firstname,String lastname) throws IOException{
		// build the URL
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    FormUtilNonMulitPart form = new FormUtilNonMulitPart(bos, null);
	    //FormUtility form = new FormUtility(bos, null);
	    form.add("firstname", firstname);
	    form.add("lastname", lastname);
	    //form.add("search", search);
	    //form.add("type", type);
	    //form.add("action", "Search");
	    //form.complete();

	    String totURL = startURL + bos.toString();
	    
	    return totURL;
	}
	
	private String returnGETURLWithAction(String startURL,String firstname,String lastname, String action) throws IOException{
		// build the URL
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    FormUtilNonMulitPart form = new FormUtilNonMulitPart(bos, null);
	    //FormUtility form = new FormUtility(bos, null);
	    form.add("firstname", firstname);
	    form.add("lastname", lastname);
	    //form.add("search", search);
	    //form.add("type", type);
	    form.add("action", action);
	    //form.complete();

	    String totURL = startURL + bos.toString();
	    
	    return totURL;
	}
	
	private static boolean advance(ParseHTML parse, String tag, int count)throws IOException{
		    
		int ch;
	    while ((ch = parse.read()) != -1)
	    {
	      if (ch == 0)
	      {
	        if (parse.getTag().getName().equalsIgnoreCase(tag))
	        {
	          count--;
	          if (count <= 0)
	            return true;
	        }
	      }
	    }
	    return false;
	}
	
	
	private static String process(ParseHTML parse,String endTag) throws IOException{
		
		StringBuilder buffer = new StringBuilder();
		boolean capture = false;
		
		int ch;
	    while ((ch = parse.read()) != -1)
	    {
	      if (ch == 0)
	      {
	        HTMLTag tag = parse.getTag();
	        if (tag.getName().equalsIgnoreCase("li"))
	        {
	          if (buffer.length() > 0)
	            processItem(buffer.toString());
	          buffer.setLength(0);
	          capture = true;
	        } else if (tag.getName().equalsIgnoreCase("/li"))
	        {
	          processItem(buffer.toString());
	          buffer.setLength(0);
	          capture = false;
	        } else if (tag.getName().equalsIgnoreCase(endTag))
	        {
	          processItem(buffer.toString());
	          break;
	        }
	      } else
	      {
	        if (capture)
	          buffer.append((char) ch);
	      }	     
	    }	
	    
	    return buffer.toString();
	}
	
	 private static void processItem(String item)
	  {
	    System.out.println(item.trim());
	  }
}
