package nl.frederik.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View extends ViewPart {
	public static final String ID = "nl.frederik.json.view";

	
	public void createPartControl(Composite parent) {
		String path = "F://test.json";
		String jsonS = getJsonString(path);
		System.out.println(jsonS);
		try {
			JSONObject obj = new JSONObject(jsonS);
			System.out.println(obj.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	private String getJsonString(String path){
		
		String ret="";
		try {
			FileReader fr = new FileReader(path);
			int i=0;
			while((i=fr.read())!=-1){
				ret = ret + i;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
		
	}

	
}