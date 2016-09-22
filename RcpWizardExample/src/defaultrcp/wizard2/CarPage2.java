package defaultrcp.wizard2;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CarPage2 extends WizardPage {

	protected CarPage2(String pageName) {
		super(pageName);
		setTitle("Travel by car");
		setDescription("Specify car");
	}

	@Override
	public void createControl(Composite parent) {
		 // create the composite to hold the widgets
		GridData gd;
		Composite composite = new Composite(parent, SWT.NONE);

	    // create the desired layout for this wizard page
		GridLayout gl = new GridLayout();
		int ncol = 2;
		gl.numColumns = ncol;
		composite.setLayout(gl);
		
	    // create the widgets. If the appearance of the widget is different from the default, 
	    // create a GridData for it to set the alignment and define how much space it will occupy
	    
	    // flights list
		Label label = new Label (composite, SWT.NONE);
		label.setText("Cars:");
		
		setControl(composite);		
		setPageComplete(true);
	}
	
	public boolean canFlipToNextPage(){
		return false;
	}

}
