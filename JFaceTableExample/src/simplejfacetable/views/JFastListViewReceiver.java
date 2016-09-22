package simplejfacetable.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import simplejfacetable.model.Person;
import simplejfacetable.providers.PersonArrayContentProvider;
import simplejfacetable.providers.PersonListLabelProvider;

public class JFastListViewReceiver extends ViewPart {

	private ListViewer listViewer;
	private List<Person> listOfPerson;
	
	
	public JFastListViewReceiver() {
		listOfPerson = new ArrayList<Person>();
	}

	@Override
	public void createPartControl(Composite parent) {
		listViewer = new ListViewer(parent, SWT.SINGLE);
		listViewer.setLabelProvider(new PersonListLabelProvider());
		listViewer.setContentProvider(new PersonArrayContentProvider());
		//listViewer.setInput(arrayOfPerson());
		
		DropTarget target = new DropTarget(listViewer.getControl(), DND.DROP_COPY);
		target.setTransfer(new Transfer[] {TextTransfer.getInstance()});
		target.addDropListener(new DropTargetAdapter(){
			@Override
			public void dragEnter(DropTargetEvent event) {
				System.out.println("dragEnter");
				event.detail = DND.DROP_COPY;
			}
						
			@Override
			public void drop(DropTargetEvent event) {
				if (event.data == null) {
					event.detail = DND.DROP_NONE;
					return;
				}
				System.out.println("drop");
				String p = (String)event.data;
				//Person[] pList = new Person[]{new Person(p, p, 4)};
				Person p2 = new Person(p,p,20);
				listOfPerson.add(p2);
				//listViewer.setInput(pList);
				listViewer.setInput(listOfPerson.toArray());
				System.out.println(p);
			}
		});

	}

	private Person[] arrayOfPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
