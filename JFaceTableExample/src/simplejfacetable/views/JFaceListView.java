package simplejfacetable.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import simplejfacetable.listeners.PersonDoubleClickedListener;
import simplejfacetable.listeners.PersonSelectionChangedListener;
import simplejfacetable.model.Person;
import simplejfacetable.providers.PersonArrayContentProvider;
import simplejfacetable.providers.PersonListLabelProvider;
import simplejfacetable.providers.PersonViewSorter;

public class JFaceListView extends ViewPart {

	private ListViewer listViewer;
	private DragSource ds;
		
	public JFaceListView() {
		// TODO Auto-generated constructor stub
			}

	@Override
	public void createPartControl(Composite parent) {
		listViewer = new ListViewer(parent, SWT.SINGLE);
		listViewer.setLabelProvider(new PersonListLabelProvider());
		listViewer.setContentProvider(new PersonArrayContentProvider());
		listViewer.setInput(listOfPersons().toArray());
		
		listViewer.setSorter(new PersonViewSorter());
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener(){

			@SuppressWarnings("unchecked")
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if(!event.getSelection().isEmpty()){
					//selection =	(IStructuredSelection) event.getSelection();
					//System.out.println("Selected: "	+ selection.getFirstElement());
					
					//selection = (Person)event.getSelection();
					//System.out.println("Selected: "	+ selection.toString());
					//selectionPerson = ((IStructuredSelection) event.getSelection()).toList();
				}
				
			}
			
		});
		listViewer.addDoubleClickListener(new PersonDoubleClickedListener());
		
		ds = new DragSource(listViewer.getControl(),DND.DROP_COPY);
		ds.setTransfer(new Transfer[] {TextTransfer.getInstance()});
		ds.addDragListener(new DragSourceListener(){

			@Override
			public void dragStart(DragSourceEvent event) {
				//event.doit=true;
				System.out.println("DragStart");
				
			}

			@Override
			public void dragSetData(DragSourceEvent event) {
				System.out.println("dragSetData");
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					Person p = (Person)((IStructuredSelection) listViewer.getSelection()).getFirstElement();
					event.data = p.toString();
					System.out.println(p.toString());
					System.out.println(p.getClass());
				}
			}

			@Override
			public void dragFinished(DragSourceEvent event) {
				System.out.println("dragFinished");				
			}
			
		});
		
		
		
	}
	
	private Person[] arrayOfPerson (){
		Person[] listPersons = new Person[]{
				new Person("Lauren", "Clayberg", 9),
				new Person("Lee", "Clayberg", 7),
				new Person("Mike", "Taylor", 55),
				new Person("Frederik","Rotsaert",41),
				new Person("Isabelle","Rotsaert",41)
		};
		return listPersons;
	}

	private List<Person> listOfPersons(){
		Person p1 = new Person("Lauren", "Clayberg", 9);
		Person p2 = new Person("Lee", "Clayberg", 7);
		Person p3 = new Person("Mike", "Taylor", 55);
		Person p4 = new Person("Frederik","Rotsaert",41);
		List<Person> lP = new ArrayList<Person>();
		lP.add(p1);lP.add(p2);lP.add(p3);lP.add(p4);
		
		return lP;
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	

}
