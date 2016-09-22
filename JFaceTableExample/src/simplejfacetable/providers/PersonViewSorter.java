package simplejfacetable.providers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import simplejfacetable.model.Person;

public class PersonViewSorter extends ViewerSorter{
	
	public int compare(Viewer viewer, Object p1,Object p2){
		return ((Person) p1).lastName
				.compareToIgnoreCase(((Person) p2).lastName);
	}
}
