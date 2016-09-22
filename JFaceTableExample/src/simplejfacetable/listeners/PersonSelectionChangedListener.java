package simplejfacetable.listeners;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import simplejfacetable.model.Person;

public class PersonSelectionChangedListener implements	ISelectionChangedListener {

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection selection =	(IStructuredSelection) event.getSelection();
			//System.out.println("Selected: "	+ selection.getFirstElement());
			Person p1 = (Person)selection.getFirstElement();
			String p1Firstname = p1.firstName;
			//System.out.println("Selected first name: " + p1Firstname);
			Object[] selected = selection.toArray();
			for(Object obj:selected){
				System.out.println(obj.toString());
			}
			
	}
}
