package httpbotexamples.chapter5;

import httpbotexamples.util.FileIOUtil;

import java.net.*;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;

 
public class AuthDownloadURL
{
  public static int BUFFER_SIZE = 8192;

   public void download(URL remoteURL, File localFile, String uid, String pwd)
      throws IOException{
	   
	   HttpURLConnection http = (HttpURLConnection) remoteURL.openConnection();
	   addAuthHeader(http, uid, pwd);
	   InputStream is = http.getInputStream();
	   OutputStream os = new FileOutputStream(localFile);
	   String type = http.getHeaderField("Content-Type").toLowerCase().trim();
	   if (type.startsWith("text"))
		   FileIOUtil.saveBinaryOrTextURL(is, os, true);
	   else
		   FileIOUtil.saveBinaryOrTextURL(is, os, false);
	   is.close();
	   os.close();
	   http.disconnect();
   }

    private void addAuthHeader(HttpURLConnection http, String uid, String pwd){
    
    	 if ((uid.length() == 0) && (pwd.length() == 0))
    	      return;
    	 String hdr = uid + ":" + pwd;
    	 String encode = base64Encode(hdr);
    	 http.addRequestProperty("Authorization", "Basic " + encode);    
    }
    
    private static void test(){
    	
    	try{
    		URL u = new URL("https://www.httprecipes.com/1/5/https.php");
    		HttpsURLConnection http = (HttpsURLConnection) u.openConnection();
    	}catch(MalformedURLException e){
    		System.out.println("Invalid URL");
    	}catch(IOException e){
    		System.out.println("Error connecting: " + e.getMessage() );
    	}
    }
    
    
   
  

    static public String base64Encode(String s)
  {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();

    Base64OutputStream out = new Base64OutputStream(bout);
    try
    {
      out.write(s.getBytes());
      out.flush();
    } catch (IOException e)
    {
    }
    finally{
    	try {
    		
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    return bout.toString();
  }

  /**
   * Overloaded version of download that accepts strings,
   * rather than URL objects.
   * 
   * @param remoteURL The URL to download from.
   * @param localFile The local file to save to.
   * @throws IOException Exception while downloading.
   */
  public void download(String remoteURL, String localFile, String uid,
      String pwd) throws IOException
  {
    download(new URL(remoteURL), new File(localFile), uid, pwd);
  }

  
  

  

  
  public static void main(String args[])
  {
    try
    {
      AuthDownloadURL d = new AuthDownloadURL();

      d.download("https://www.httprecipes.com/1/5/secure/", 
            "./test.html",
            "testuser", 
            "testpassword");      
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}