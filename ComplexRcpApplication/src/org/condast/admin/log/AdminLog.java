package org.condast.admin.log;

import org.condast.admin.AdminActivator;
import org.condast.admin.Application;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class AdminLog {

	/**
	 * Log an info message
	 * @param message
	 */
	public static void logInfo( String message ){
		log( IStatus.INFO, IStatus.OK, message, null );
	}

	/**
	 * Log an error message
	 * @param message
	 */
	public static void logError( String message, Throwable exception ){
		log( IStatus.ERROR, IStatus.OK, message, exception );
	}

	/**
	 * Log an error message
	 * @param message
	 */
	public static void logError( Throwable exception ){
		logError( "An unexpected excpetion was thrown", exception );
	}

	public static void log( int severity, int code, String message, Throwable exception ){
		AdminActivator.getDefault().getLog().log( createStatus(severity, code, message, exception));
	}

	private static IStatus createStatus( int severity, int code, String message, Throwable exception ){
		return new Status( severity, Application.S_PLUGIN_ID, code, message, exception );
	}
	public static void log( IStatus status ){
		AdminActivator.getDefault().getLog().log( status );
	}
}
