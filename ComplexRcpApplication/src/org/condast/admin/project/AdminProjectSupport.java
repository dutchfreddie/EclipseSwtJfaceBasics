package org.condast.admin.project;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.condast.admin.log.AdminLog;
import org.condast.admin.utils.Utils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class AdminProjectSupport {

	public static IProject createProject( String name, Date date, URI location, Path[] paths ){
		IProject project = createBaseProject( name, date, location );
		try{
			addNature( project );
			addToProjectStructure( project, paths );
		}
		catch( CoreException ex ){
			AdminLog.logError(ex );
		}
		return project;
	}

	public static Path[] createPathsFromString( String[] names ){
		Collection<Path> paths=  new ArrayList<Path>();
		for( String name: names ){
			paths.add( new Path( name ));
		}
		return paths.toArray( new Path[ paths.size()]);
	}
	
	static void addToProjectStructure(IProject project, Path[] paths) {
		for (Path path : paths) {
			IFolder etcFolders = project.getFolder(path);
			try {
				createFolder(etcFolders);
			} catch (CoreException e) {
				AdminLog.logError( e );
			}
		}	
	}

	/**
	 * Create a new project for the given year
	 * @param date
	 * @param location
	 * @return
	 */
	static IProject createBaseProject( String name, Date date, URI location) {
		if( date == null )
			return null;
		SimpleDateFormat format = new SimpleDateFormat( "YYYY" ); 
		String year = format.format(date);
		if( Utils.isNull( name )){
			name = year;
		}else{
			name += "-" + year;
		}
		// it is acceptable to use the ResourcesPlugin class
		SimpleDateFormat formatter = new SimpleDateFormat( "YYYY");
		IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject( formatter.format( date ));
		if (!newProject.exists()) {
			URI projectLocation = location;
			IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
			if ((location != null ) && (ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location))) {
				projectLocation = null;
			}

			desc.setLocationURI(projectLocation);
			try {
				newProject.create(desc, null);
				if (!newProject.isOpen()) {
					newProject.open(null);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return newProject;	
	}
	
	/**
	 * Create a folder in the project
	 * @param folder
	 * @throws CoreException
	 */
	static void createFolder(IFolder folder) throws CoreException {
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder) {
			createFolder((IFolder) parent);
		}
		if (!folder.exists()) {
			folder.create(false, true, null);
		}
	}
	
	/**
	 * Add a nature to the project
	 * @param project
	 * @throws CoreException
	 */
	static void addNature(IProject project) throws CoreException {
		if (!project.hasNature(ProjectNature.S_NATURE_ID)) {
			IProjectDescription description = project.getDescription();
			String[] prevNatures = description.getNatureIds();
			String[] newNatures = new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length] = ProjectNature.S_NATURE_ID;
			description.setNatureIds(newNatures);

			IProgressMonitor monitor = null;
			project.setDescription(description, monitor);
		}
	}

}
