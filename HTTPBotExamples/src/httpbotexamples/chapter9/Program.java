package httpbotexamples.chapter9;

public class Program {

	public static void main(String[] args) {
		
		String s = "iiiii<center>Test</center>cccccc<center>Test2</center>yyyyy";//Test2</center><center>Test3</center>";
		String token1 = "<center>";
		String token2 = "</center>";
		int x = s.indexOf(s, 0);
		//System.out.println(x + s2);
		int count = 0;
		
		
		
		int location1, location2;

	    // convert everything to lower case
	    String searchStr = s.toLowerCase();
	    token1 = token1.toLowerCase();
	    token2 = token2.toLowerCase();

	    // now search
	    location1 = location2 = 0;
	    do
	    {
	      location1 = searchStr.indexOf(token1, location1+1);

	      if (location1 == -1)
	        break;

	      count--;
	    } while (count > 0);

	    // return the result from the original string that has mixed
	    // case
	    location2 = s.indexOf(token2, location1 + 1);
	    System.out.println(location1 + " " + location2);

	    String substract = s.substring(location1 + token1.length(), location2);
	    System.out.println("**" + substract + "**");
	    
	    
		
	}

}
