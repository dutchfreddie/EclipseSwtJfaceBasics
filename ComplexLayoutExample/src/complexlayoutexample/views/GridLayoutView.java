package complexlayoutexample.views;

import java.util.ArrayList;




import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class GridLayoutView extends ViewPart {

	public static final String ID = "ComplexLayoutExample.view.GridLayoutView";
	
	public GridLayoutView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		
		layout.numColumns=3;
		layout.horizontalSpacing=20;
		parent.setLayout(layout);
		Label label = new Label(parent,SWT.BOLD|SWT.TOP);
		label.setText("A very Text");
		GridData gd1 = new GridData();
		gd1.verticalAlignment=GridData.FILL;
		gd1.grabExcessHorizontalSpace=true;
		gd1.minimumWidth=100;
		label.setLayoutData(gd1);
		
		Combo combo = new Combo (parent, SWT.READ_ONLY);
		combo.setItems (new String [] {"Alpha", "Bravo", "Charlie"});
		GridData gd2 = new GridData();
		gd2.verticalAlignment=GridData.BEGINNING;
		//gd1.grabExcessHorizontalSpace=true;
		//gd1.minimumWidth=200;
		
		combo.setLayoutData(gd2);
		
		List list = new List(parent,SWT.BORDER|SWT.MULTI|SWT.V_SCROLL);
		for (int i=0; i<128; i++) list.add ("Item " + i);
		GridData gd3 = new GridData();
		gd3.horizontalAlignment=GridData.FILL;
		gd3.verticalAlignment=GridData.FILL;
		gd3.grabExcessHorizontalSpace=true;
		gd3.grabExcessVerticalSpace=true;
		
		list.setLayoutData(gd3);
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	private void layoutButtons(Composite parent){
		GridLayout layout = new GridLayout();
		//layout.makeColumnsEqualWidth=true;
		layout.numColumns=3;
		parent.setLayout(layout);
		java.util.List<Button> listButtons = new ArrayList<Button>();
		for(int i =1;i<20;i++){
			Button b = new Button(parent,SWT.PUSH);
			if(i==3){
				b.setText("ButtonButton " + i);
				GridData gridData = new GridData();
				gridData.horizontalAlignment=GridData.END;
				//gridData.grabExcessHorizontalSpace = true;
				b.setLayoutData(gridData);
			}	
			else if(i==12) {
				b.setText("Button " +i);
				GridData gridData = new GridData();
				//gridData.horizontalAlignment=GridData.BEGINNING;
				gridData.grabExcessHorizontalSpace = true;
				b.setLayoutData(gridData);
			}
			else if(i==7){
				b.setText("ButtonB " + i);
				Text text = new Text(parent,SWT.BORDER_SOLID);
				text.setText("Some Content");
			}
			
			else{
				b.setText("Button " +i);
				GridData gridData = new GridData();
				gridData.horizontalAlignment=GridData.FILL;
				gridData.grabExcessHorizontalSpace = true;
				b.setLayoutData(gridData);
				
			}
				
			
			listButtons.add(b);
					
		}
	}

}
