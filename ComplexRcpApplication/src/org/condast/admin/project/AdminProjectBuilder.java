package org.condast.admin.project;

import java.util.Map;

import org.condast.admin.utils.StringStyler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;

public class AdminProjectBuilder extends IncrementalProjectBuilder {

	public static String S_PLUGIN_XML = "plugin.xml";
	public static String S_PLUGIN_PROPERTIES = "plugin.properties";
	
	public static String S_MSG_START_AUDIT = "Auditing Admin Project";
	
	public enum Violations{
		MISSING_KEY,
		UNUSED_KEY;

		@Override
		public String toString() {
			return StringStyler.prettyString( super.toString() );
		}
	}
	public AdminProjectBuilder() {
	}

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if( shouldAudit( kind )){
			auditPluginManifest( monitor );
		}
		final IProject project = getProject();
		this.setupNewProject();
		   
		return null;
	}
	
	/**
	 * Checks for the conditions of a full build, ( plufin.xml or plugin properties have changed)
	 * @see Eclipse Plugins pg 541
	 * @param kind
	 * @return
	 */
	private boolean shouldAudit( int kind ){
		if( kind == FULL_BUILD )
		  return true;
		IResourceDelta delta = getDelta( getProject() );
		if( delta == null )
			return false;
		IResourceDelta[] children = delta.getAffectedChildren();
		for( IResourceDelta child: children ){
			String fileName = child.getProjectRelativePath().lastSegment();
			if( fileName.equals( S_PLUGIN_XML) || fileName.equals( S_PLUGIN_PROPERTIES ))
				return true;
		}
		return false;
	}
	
	private void auditPluginManifest( IProgressMonitor monitor ){
		monitor.beginTask( S_MSG_START_AUDIT, 4 );
		IProject project = getProject();
		if( checkCancel( monitor))
			return;
		monitor.done();
	}

	private void setupNewProject(){
		final IProject project = getProject();
		IWorkspace workspace = project.getWorkspace();
		IWorkspaceRunnable operation = new IWorkspaceRunnable() {
		      public void run(IProgressMonitor monitor) throws CoreException {
		         int fileCount = 10;
		         project.create(null);
		         project.open(null);
		         for (int i = 0; i < fileCount; i++) {
		            IFile file = project.getFile("File" + i);
		            file.create(null, IResource.NONE, null);
		         }
		      }
		   };
		   try {
			workspace.run(operation, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Perform a check to see if the build should be cancelled
	 * @param monitor
	 * @return
	 */
	private boolean checkCancel( IProgressMonitor monitor ){
		if( monitor.isCanceled() )
			throw new OperationCanceledException();
		return isInterrupted();
	}
}
