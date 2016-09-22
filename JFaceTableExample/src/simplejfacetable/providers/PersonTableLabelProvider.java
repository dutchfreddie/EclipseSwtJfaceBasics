package simplejfacetable.providers;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import simplejfacetable.model.Person;

public class PersonTableLabelProvider extends LabelProvider implements ITableLabelProvider{

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Person person = (Person) element;
		switch (columnIndex) {
		case 0 :
			return person.firstName;
		case 1 :
			return person.lastName;
		case 2 :
			return Integer.toString(person.age);
		default:
			return "unknown " + columnIndex;
		}
	}
}
