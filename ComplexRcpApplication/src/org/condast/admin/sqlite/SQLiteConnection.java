package org.condast.admin.sqlite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.*;


public class SQLiteConnection
{
	 public static final String S_SQLITE_DB = "fiscaldb.sqlite";

	private Connection connection;
	
	private File file;

	
	public SQLiteConnection( File file )
	{
		this.file = file;
	}

	public void open() throws IOException
	{
		try {
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e1) {
			throw new IOException( e1 );
		}
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
		}
		catch (SQLException e) {
			throw new IOException( e );
		}
	}

	/**
	 * @return the connection
	 */
	public final Connection getConnection()
	{
		return connection;
	}

	protected void execute( File file, String query ) throws ClassNotFoundException{
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.
			ResultSet rs = statement.executeQuery( query );
			while(rs.next())
			{
				// read the result set
				//System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}

	public void close() throws IOException
	{
		try
		{
			if(connection != null)
				connection.close();
		}
		catch(SQLException e)
		{
			throw new IOException(e);
		}
	}

	private Connection sqlConn;
	
	


}
