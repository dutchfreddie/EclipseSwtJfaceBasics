package simplejfacetable.views;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import simplejfacetable.listeners.PersonSelectionChangedListener;
import simplejfacetable.model.Person;
import simplejfacetable.providers.PersonTableLabelProvider;
import simplejfacetable.providers.PersonViewSorter;

public class JFaceTableView extends ViewPart {

	private TableViewer tableViewer;
	private Table table;
	
	public JFaceTableView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		tableViewer = new TableViewer(parent, SWT.SINGLE|SWT.FULL_SELECTION);
		
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		String[] columnNames = new String[] {"First Name", "Last Name","age"};
		int[] columnWidths = new int[] {100, 100, 75};
		int[] columnAlignments = new int[] {SWT.LEFT, SWT.LEFT, SWT.CENTER};
		for (int i = 0; i < columnNames.length; i++) {
			TableColumn tableColumn =	new TableColumn(table, columnAlignments[i]);
			tableColumn.setText(columnNames[i]);
			tableColumn.setWidth(columnWidths[i]);
		}
		
		tableViewer.setLabelProvider(new PersonTableLabelProvider());
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setInput(arrayOfPerson());
		tableViewer.setSorter(new PersonViewSorter());
		tableViewer.addSelectionChangedListener(new PersonSelectionChangedListener());
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	private Person[] arrayOfPerson (){
		Person[] listPersons = new Person[]{
				new Person("Lauren", "Clayberg", 9),
				new Person("Lee", "Clayberg", 7),
				new Person("Mike", "Taylor", 55),
				new Person("Frederik","Rotsaert",41),
				new Person("Frederik","Rotsaert",41)
		};
		return listPersons;
	}

}
