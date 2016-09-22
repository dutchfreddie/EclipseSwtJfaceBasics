package complexlayoutexample.views;



import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class SimpleFormLayoutView extends ViewPart {

	public static final String ID = "ComplexLayoutExample.view.SimpleFormLayoutView";
	
	public SimpleFormLayoutView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {		
		
		Layout l = new GridLayout();
		
		
		GridData gridData = new GridData();
		GridLayout gridLayout = new GridLayout();
		
		
		gridData.grabExcessHorizontalSpace = false;
		
		Group mainGroup = createGroup(parent,1,"MainGroup",gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment=GridData.CENTER;
		gridData.grabExcessHorizontalSpace=true;
		
		Group g = createGroup(mainGroup, 1, "", gridData);
		Label label=new Label(g,SWT.CENTER);
		label.setText("Main Label");
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		
		g = createGroup(mainGroup,2,"Group2",gridData);
		
		
		label = new Label(g,SWT.NONE);
		label.setText("Column 1");
		Text text1 = new Text(g,SWT.SINGLE | SWT.BORDER);
		text1.setText("Columns1");
		label = new Label(g,SWT.NONE);
		label.setText("Column 22222");
		Text text2 = new Text(g,SWT.SINGLE | SWT.BORDER);
		text2.setText("Columns2");
		
		//Group 3
		
		gridLayout= new GridLayout();
		gridLayout.numColumns=2;
		gridLayout.makeColumnsEqualWidth=false;
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL_HORIZONTAL;
		gridData.grabExcessHorizontalSpace = true;
		
		g = createGroup(mainGroup,"Group3",gridLayout,gridData);		
		
		label = new Label(g,SWT.NULL);
		label.setText("Column 1");
		label = new Label(g,SWT.NULL);
		label.setText("Column 22222");
		text1 = new Text(g,SWT.SINGLE | SWT.BORDER);
		text1.setText("Columns1");	
		text1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text2 = new Text(g,SWT.SINGLE | SWT.BORDER);
		text2.setText("Columns2");
		text2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	protected Group createGroup(Composite parent, int numOfColumns, String text,GridData data) {
		
		Group retval = new Group(parent, SWT.NONE);
		retval.setLayout(new GridLayout(numOfColumns, false));
		retval.setLayoutData(data);
		if (text!="") {
			retval.setText(text);
		}
		return retval;
	}	
	
protected Group createGroup(Composite parent, String text,GridLayout gridLayout, GridData data) {
		
		Group retval = new Group(parent, SWT.NONE);
		retval.setLayout(gridLayout);
		retval.setLayoutData(data);
		if (text!="") {
			retval.setText(text);
		}
		return retval;
	}	
	
}
