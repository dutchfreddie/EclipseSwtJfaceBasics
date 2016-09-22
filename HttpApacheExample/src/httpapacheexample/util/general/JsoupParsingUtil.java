package httpapacheexample.util.general;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupParsingUtil {
	
	public static void saveTagContentToFile(String outputFileURLTot,String outputFileURLBody,String tag) throws IOException{
		File in = new File(outputFileURLTot);
		Document doc = Jsoup.parse(in, "UTF-8");
		Elements elements = doc.select(tag);
		String html = elements.html();
		FileIOUtil.writeStringToFile(html, outputFileURLBody);
	}	

}
