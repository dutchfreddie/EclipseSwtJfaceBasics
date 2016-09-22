package defaultrcp.wizard2;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class WalkingPage2 extends WizardPage {

	private List<String> list;
	
		
	protected WalkingPage2(String pageName) {
		super(pageName);
		setTitle("Travel by foot");
		setDescription("Specify footwear");
	}
	
	protected WalkingPage2(String pageName, List<String> list){
		super(pageName);
		this.list=list;
		setTitle("Travel by foot");
		setDescription("Specify footwear");
	}	

	@Override
	public void createControl(Composite parent) {
		 // create the composite to hold the widgets
		GridData gd;
		Composite composite = new Composite(parent, SWT.NONE);

	    // create the desired layout for this wizard page
		GridLayout gl = new GridLayout(2,true);
		composite.setLayout(gl);
		
	    // create the widgets. If the appearance of the widget is different from the default, 
	    // create a GridData for it to set the alignment and define how much space it will occupy
	    
	    // flights list
		Label label = new Label (composite, SWT.NONE);
		label.setText("Walking:");
		
		list = ((HolidayWizard2)getWizard()).getList();
		if(list!=null){
			for(String s:list){
				label = new Label(composite,SWT.NONE);
				label.setText(s);
			}
		}
		
		setControl(composite);		
		setPageComplete(true);

	}

}
