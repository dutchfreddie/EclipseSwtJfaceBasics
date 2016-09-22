package org.condast.admin.fiscalyear;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.condast.admin.log.AdminLog;

public class FiscalYear 
{
	  //The name of the root concept
	  public static final String S_APPLICATION = "Application";
	  public static final String S_APPLICATION_NAME = ".condastAdmin";
	  public static final String S_DATABASE = "Database";
	  public static final String S_CONFIG = "config";
	  public static final String S_USER_HOME_PROPERTY = "user.home";
	  public static final String S_FISCAL_YEAR = "Fiscal year: ";

	  private Path path;
	  private String identifier;
	
	public FiscalYear( String location, String identifier ){
		path = Paths.get( location );
		this.identifier = identifier;
	}

	public FiscalYear( URI location, String identifier ){
		path = Paths.get( location );
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Path getPath() {
		return path;
	}

	/**
	 * Create a new fiscal year environment
	 * @return
	 */
	public boolean create(){
		try {
			if( Files.exists( path ))
				return false;
			Files.createDirectories (path );
			return true;
		} catch (IOException e) {
			AdminLog.logError( e );
			return false;
		}
	}

	public boolean isValidPath(){
		File file = path.toFile();
		return ( file.exists() && file.isDirectory() );
	}
	
	/**
	 * Add a bank to the system
	 * @param bankName
	 * @return
	 */
	public final Path addBank( String bankName){
		return Paths.get( this.path + bankName );
	}

	/**
	 * Remove a bank from the system
	 * @param bankName
	 * @return
	 */
	public final Path removeBank( String bankName){
		Path path = Paths.get( this.path + bankName );
		path.toFile().delete();
		return path;
	}

	/**
	 * Return the default user directory. This is '%system-user%\<folder>\'
	 * @param aieon
	 * @return
	 */
	public static URI getPublicDatabaseDir( String folder )
	{
		File file = new File( S_DATABASE + File.separator + 
			folder + File.separator );
		return file.toURI();
	}

	/**
	 * Return the default user directory. This is '%system-user%\<folder>\'
	 * @param aieon
	 * @return 
	 */
	public static URI getDefaultPublicDatabase( String folder, String name )
	{
		File file = new File( S_DATABASE + File.separator + folder + 
			 File.separator + name + ".sqlite" );
		return file.toURI();
	}

	/**
	 * Return the default user directory. This is '%system-user%\<folder>\'
	 * @param aieon
	 * @return
	 */
	public static URI getDefaultUserDir( String folder )
	{
		File file = new File( System.getProperty( S_USER_HOME_PROPERTY ) + File.separator +
			"." + folder + File.separator );
		return file.toURI();
	}
	
	/**
	 * Return the default user directory. This is '%system-user%\<folder>\'
	 * @param aieon
	 * @return 
	 */
	public static URI getDefaultUserDatabase( String folder, String name )
	{
		File file = new File( System.getProperty( S_USER_HOME_PROPERTY ) + File.separator + 
			folder + File.separator + name + ".sqlite" );
		return file.toURI();
	}
	
	public static final FiscalYear createNewFiscalYear( Date date ){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY");
		String year = formatter.format(date);
		String folder = S_APPLICATION_NAME + File.separator + year;
		FiscalYear fy = new FiscalYear( getDefaultUserDir( folder ), S_FISCAL_YEAR + year );
		fy.create();
		return fy;
	}
}
