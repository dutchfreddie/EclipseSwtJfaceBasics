package httpapacheexample.util.general;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileIOUtil {

	
	// the size of a buffer
	  public static int BUFFER_SIZE = 8192;
	  
	public static void saveBinaryOrTextURL(InputStream is,OutputStream os, boolean isTekst) throws IOException{
		if(isTekst){
			StringBuffer sb = new StringBuffer();
			byte lineSep[] = System.getProperty("line.separator").getBytes();
			int ch = 0;
			boolean inLineBreak = false;
			boolean hadLF = false;
			boolean hadCR = false;
			do{
				ch = is.read();
				if (ch != -1){
					if ((ch == '\r') || (ch == '\n')){
						inLineBreak = true;
						if (ch == '\r'){
							if (hadCR)
								os.write(lineSep);
							else
								hadCR = true;
						} 
						else{
							if (hadLF)
								os.write(lineSep);
							else
								hadLF = true;
						}
						sb.append(Character.toChars(ch));
			        } 
					else{
						if (inLineBreak){
							os.write(lineSep);
							hadCR = hadLF = inLineBreak = false;
						}
						char c = (char)ch;
						sb.append(c);
						//sb.append(Character.toChars(ch));
						os.write(ch);
					}
				}
			} 
			while (ch != -1);
			
			System.out.println(sb.toString());
		}
		else{
			StringBuilder result = new StringBuilder();
			byte buffer[] = new byte[BUFFER_SIZE];
			
			int size = 0;

		    do
		    {
		      size = is.read(buffer);
		      if (size != -1)
		        result.append(new String(buffer, 0, size));
		    } while (size != -1);

		    String totalString = os.toString();
		    System.out.println(totalString);
		    os.write(totalString.getBytes());
		    os.close();			    
		}
	}
	
	public static void saveTextURLtoFile(InputStream is,String fileTo) throws IOException{
		StringBuffer sb = new StringBuffer();
		OutputStream os = new FileOutputStream(fileTo);
		byte lineSep[] = System.getProperty("line.separator").getBytes();
		int ch = 0;
		boolean inLineBreak = false;
		boolean hadLF = false;
		boolean hadCR = false;
		do{
			ch = is.read();
			if (ch != -1){
				if ((ch == '\r') || (ch == '\n')){
					inLineBreak = true;
					if (ch == '\r'){
						if (hadCR)
							os.write(lineSep);
						else
							hadCR = true;
					} 
					else{
						if (hadLF)
							os.write(lineSep);
						else
							hadLF = true;
					}
					sb.append(Character.toChars(ch));
		        } 
				else{
					if (inLineBreak){
						os.write(lineSep);
						hadCR = hadLF = inLineBreak = false;
					}
					char c = (char)ch;
					sb.append(c);
					//sb.append(Character.toChars(ch));
					os.write(ch);
				}
			}
		} 
		while (ch != -1);
		
		os.close();
	}
	
	public static void saveTextURL(InputStream is,OutputStream os) throws IOException{
		StringBuffer sb = new StringBuffer();
		byte lineSep[] = System.getProperty("line.separator").getBytes();
		int ch = 0;
		boolean inLineBreak = false;
		boolean hadLF = false;
		boolean hadCR = false;
		do{
			ch = is.read();
			if (ch != -1){
				if ((ch == '\r') || (ch == '\n')){
					inLineBreak = true;
					if (ch == '\r'){
						if (hadCR)
							os.write(lineSep);
						else
							hadCR = true;
					} 
					else{
						if (hadLF)
							os.write(lineSep);
						else
							hadLF = true;
					}
					sb.append(Character.toChars(ch));
		        } 
				else{
					if (inLineBreak){
						os.write(lineSep);
						hadCR = hadLF = inLineBreak = false;
					}
					char c = (char)ch;
					sb.append(c);
					//sb.append(Character.toChars(ch));
					os.write(ch);
				}
			}
		} 
		while (ch != -1);
	}
	
	public static void saveBinaryToFile(InputStream is,OutputStream os) throws IOException{
		StringBuilder result = new StringBuilder();
		byte buffer[] = new byte[BUFFER_SIZE];
		
		int size = 0;

	    do
	    {
	      size = is.read(buffer);
	      if (size != -1)
	        result.append(new String(buffer, 0, size));
	    } while (size != -1);

	    String totalString = os.toString();
	    System.out.println(totalString);
	    os.write(totalString.getBytes());
	    os.close();			
	}
	
	public static void writeStringToFile(String out,String fileOutput) throws IOException{
		File file = new File(fileOutput);
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(out);
		bw.close();
		
	}
	
	public static String returnHTMLPage(InputStream is) throws IOException{
		
		StringBuffer os = new StringBuffer();
		byte lineSep[] = System.getProperty("line.separator").getBytes();
	    int ch = 0;
	    boolean inLineBreak = false;
	    boolean hadLF = false;
	    boolean hadCR = false;

	    do
	    {
	      ch = is.read();
	      if (ch != -1)
	      {
	        if ((ch == '\r') || (ch == '\n'))
	        {
	          inLineBreak = true;
	          if (ch == '\r')
	          {
	            if (hadCR)
	              os.append(lineSep);
	            else
	              hadCR = true;
	          } else
	          {
	            if (hadLF)
	              os.append(lineSep);
	            else
	              hadLF = true;
	          }
	        } else
	        {
	          if (inLineBreak)
	          {
	            os.append(lineSep);
	            hadCR = hadLF = inLineBreak = false;
	          }
	          os.append(ch);
	        }
	      }
	    } while (ch != -1);
	    
	    return os.toString();
	}
	
}
