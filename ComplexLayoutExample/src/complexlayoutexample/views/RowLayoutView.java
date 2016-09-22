package complexlayoutexample.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class RowLayoutView extends ViewPart {

	public static final String ID = "ComplexLayoutExample.view.RowLayoutView";
	
	public RowLayoutView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		RowLayout layout = new RowLayout();
		layout.spacing=20;
		layout.marginWidth=10;
		layout.marginHeight=100;
		layout.type=SWT.VERTICAL;
		layout.center = true;
		//layout.wrap=true;
		//layout.pack=false;
		//layout.justify=true;
		parent.setLayout(layout);
		
		Button b = new Button(parent,SWT.NONE);
		b.setText("Button");
		b = new Button(parent,SWT.NONE);
		b.setText("ButtonButton");
		b = new Button(parent,SWT.NONE);
		b.setText("Button");
		b = new Button(parent,SWT.NONE);
		b.setText("Button");
		b = new Button(parent,SWT.NONE);
		b.setText("Button");
		RowData d = new RowData();
		d.height=100;
		d.width=20;
		b.setLayoutData(d);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
