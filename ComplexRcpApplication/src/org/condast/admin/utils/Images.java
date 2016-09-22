package org.condast.admin.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public class Images {

	public static final String S_ICON_PATH = "/resources/";

	public static final String S_ERROR_ICON = "error_button_16.png";
	public static final String S_MODIFIED_ICON = "orangeball.jpg";
	public static final String S_BLANK_ICON = "blank.png";
	public static final String S_NEW_PROJECT = "folder_new.png";
	public static final String S_NEW_FILE = "document_new.png";

	private Collection<ImageData> imageMap;
	private String path;
	
	private static Images images = new Images();
	
	private Images( ) {
		imageMap = new ArrayList<ImageData>();
		this.path = S_ICON_PATH;
		this.initialise();
	}
	
	public static Images getInstance(){
		return images;
	}
	
	public void initialise(){
		setImage( S_BLANK_ICON );
		setImage( S_ERROR_ICON );
		setImage( S_MODIFIED_ICON );		
		setImage( S_NEW_PROJECT );		
		setImage( S_NEW_FILE );		
	}

	protected void setImage( String name ){
		ImageData data = new ImageData();
		data.location = path + name;
		data.descriptor = getImageDescriptor( data.location ); 
		data.image = data.descriptor.createImage();
		imageMap.add( data );	
	}

	public Image getImage( String name ){
		for( ImageData data: imageMap ){
			if( data.location.endsWith( name ))
				return data.image;
		}
		return null;
	}

	public ImageDescriptor getImageDescriptor( String name ){
		for( ImageData data: imageMap ){
			if( data.location.endsWith( name ))
				return data.descriptor;
		}
		return null;
	}

	public void dispose(){
		for( ImageData data: imageMap )
			data.image.dispose();
		imageMap.clear();
	}

    protected ImageDescriptor getImageDescriptorFromLocation(String location ) {
       URL url = this.getClass().getResource( location );
	   return ImageDescriptor.createFromURL(url);
    }
}

class ImageData{	
	String location;
	ImageDescriptor descriptor;
	Image image;
}
