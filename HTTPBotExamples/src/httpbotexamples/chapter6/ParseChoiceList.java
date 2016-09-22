package httpbotexamples.chapter6;

import java.io.*;
import java.net.*;

import httpbotexamples.util.*;

public class ParseChoiceList
{

  /**
   * Called for each option item that is found. 
   * @param name The name of the option item.
   * @param value The value of the option item.
   */
  private static void processOption(String name, String value)
  {
    StringBuilder result = new StringBuilder();
    result.append('\"');
    result.append(name);
    result.append("\",\"");
    result.append(value);
    result.append('\"');
    System.out.println(result.toString());
  }

  private boolean advance(ParseHTML parse, String tag, int count)
      throws IOException
  {
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

    public String process(URL url, int optionList) throws IOException
  {
    String value = "";
    InputStream is = url.openStream();
    ParseHTML parse = new ParseHTML(is);
    StringBuilder buffer = new StringBuilder();

    //advance(parse, "select", optionList);

    int ch;
    while ((ch = parse.read()) != -1)
    {
      if (ch == 0)
      {
        HTMLTag tag = parse.getTag();
        if (tag.getName().equalsIgnoreCase("option"))
        {
          value = tag.getAttributeValue("value");
          buffer.setLength(0);
        } else if (tag.getName().equalsIgnoreCase("/option"))
        {
          processOption(buffer.toString(), value);
        } else if (tag.getName().equalsIgnoreCase("/choice"))
        {
          break;
        }
      } else
      {
        buffer.append((char) ch);
      }
    }
    
    return buffer.toString();
  }

  
  /**
   * The main method, create a new instance of the object and call
   * process.
   * @param args not used.
   */
  public static void main(String args[])
  {
	  URL u;
	try {
		u = new URL("http://www.httprecipes.com/1/6/form.php");
		HttpURLConnection http = (HttpURLConnection) u.openConnection();
		http.connect();
		InputStream is = http.getInputStream();
		processInputStream(is,1);
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
  
  private static String processInputStream(InputStream is, int optionList) throws IOException
  {
    String value = "";
    ParseHTML parse = new ParseHTML(is);
    StringBuilder buffer = new StringBuilder();

    //advance(parse, "select", optionList);

    int ch;
    while ((ch = parse.read()) != -1)
    {
      if (ch == 0)
      {
        HTMLTag tag = parse.getTag();
        if (tag.getName().equalsIgnoreCase("option"))
        {
          value = tag.getAttributeValue("value");
          buffer.setLength(0);
        } else if (tag.getName().equalsIgnoreCase("/option"))
        {
          processOption(buffer.toString(), value);
        } else if (tag.getName().equalsIgnoreCase("/choice"))
        {
          break;
        }
      } else
      {
        buffer.append((char) ch);
      }
    }
    
    return buffer.toString();
  }
  
  
  private static void simpleProcess(){
	  try
	    {
	      URL u = new URL("http://www.httprecipes.com/1/6/form.php");
	      ParseChoiceList parse = new ParseChoiceList();
	      String output = parse.process(u, 1);
	      System.out.println(output);
	    } catch (Exception e)
	    {
	      e.printStackTrace();
	    }
  }
}