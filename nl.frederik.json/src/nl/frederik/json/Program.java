package nl.frederik.json;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;



public class Program {

	public static void main(String[] args) {
		
		String path = "F://test2.json";
		String jsonS = getJsonString(path);
		System.out.println(jsonS);
		
			
			

	}
	
	private static void jsonParsingEmployees(String jsonS){
		System.out.println(jsonS);
		try {
			JSONObject obj = new JSONObject(jsonS);
			System.out.println(obj.toString());
			JSONArray objA = obj.getJSONArray("employees");
			System.out.println(objA.toString());
			for(int i=0;i<objA.length();i++){
				JSONObject obj1 = objA.getJSONObject(i);
				System.out.println(obj1);
				String first = obj1.getString("firstName");
				String last = obj1.getString("lastName");
				System.out.println(first + "\t" + last);
			}
			
			JSONTokener tokener = new JSONTokener(jsonS);
			System.out.println(tokener.next());
			System.out.println(tokener.next());
			System.out.println(tokener.next());
			System.out.println(tokener.next());
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String getJsonString(String path){
		
		String ret="";
		try {
			FileReader fr = new FileReader(path);
			int i=0;
			while((i=fr.read())!=-1){
				ret = ret + (char)i;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
		
	}


}
