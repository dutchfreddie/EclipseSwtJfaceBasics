package simplejfacetable.providers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import simplejfacetable.model.Person;

public class PersonListLabelProvider extends LabelProvider {
	
	public String getText(Object element) {
		Person person = (Person) element;
		String ret = "persoon: " + person.firstName + " " + person.lastName + " " + person.age; 
		return ret;
	}
}
