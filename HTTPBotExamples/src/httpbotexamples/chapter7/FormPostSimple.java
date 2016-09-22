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
import java.net.URLConnection;

public class FormPostSimple {

	public static void main(String args[]){
		try{
	      FormPostSimple parse = new FormPostSimple();
	      String startURL = "http://localhost/phpexample/simplewelkomPost.php?";
	      URL url = new URL(startURL);
	      URLConnection http = url.openConnection();
	      http.setDoOutput(true);
	      OutputStream os = http.getOutputStream();     
	      String firstname = "Frederik";
	      String lastname = "Rotsaert";
	      
	      FormUtilNonMulitPart form = new FormUtilNonMulitPart(os, null);
		    //FormUtility form = new FormUtility(bos, null);
		    form.add("firstname", firstname);
		    form.add("lastname", lastname);
		 // read the results
		    InputStream is = http.getInputStream();
		    //InputStream is2 = http.getInputStream();
		    //OutputStream os2 = new FileOutputStream("./textpost.html");
		    //FileIOUtil.saveBinaryOrTextURL(is2, os2, true);
		    
	      
	      
	      
	      ParseHTML parseHTML = new ParseHTML(is);
	      
	      
	      String beginTag="ul";
	      String endTag="/ul";
	      int count = 0;
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
