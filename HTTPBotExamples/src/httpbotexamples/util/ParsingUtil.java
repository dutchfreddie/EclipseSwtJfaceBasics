package httpbotexamples.util;

public class ParsingUtil {

	public static String extractBetweenElements(String str, String token1, String token2, int count)
	  {
	    int location1, location2;

	    location1 = location2 = 0;
	    do
	    {
	      location1 = str.indexOf(token1, location1 + 1);

	      if (location1 == -1)
	        return null;

	      count--;
	    } while (count > 0);

	    location2 = str.indexOf(token2, location1 + 1);
	    if (location2 == -1)
	      return null;

	    return str.substring(location1 + token1.length(), location2);
	  }
	
}
