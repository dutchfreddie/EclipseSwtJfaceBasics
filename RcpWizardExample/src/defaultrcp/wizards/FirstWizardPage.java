package defaultrcp.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
public class FirstWizardPage extends WizardPage {

	private Text text;
	
	public FirstWizardPage(){
		super("");
		this.setTitle("FirstWizardPage");
		this.setDescription("description of FirstWizardPage");
	}
	
	public FirstWizardPage(String pageName, String title){
		super(pageName);
		this.setTitle(title);
	}
	
	public FirstWizardPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parentControl) {
		//AbstractComposite composite = new AbstractComposite(parent, SWT.NONE);
		Composite parent = new Composite(parentControl,SWT.NULL);
		 parent.setLayout(new GridLayout(5, false));
		    GridData griddata = new GridData();
		    griddata.horizontalSpan = 5;
		    final Label label = new Label(parent, SWT.NONE);
		    label.setText("A first line of text in this place");
		    label.setLayoutData(griddata);
		    new Label(parent, SWT.NONE);
		    
		    Label lblNewLabel = new Label(parent, SWT.NONE);
		    lblNewLabel.setText("Fill In Some Text");
		    new Label(parent, SWT.NONE);
		    
		    text = new Text(parent, SWT.BORDER);
		    text.addModifyListener(new ModifyListener(){

				@Override
				public void modifyText(ModifyEvent e) {
					Text text = (Text) e.widget;
			        System.out.println(text.getText());
					
				}
		    	
		    });
		      
		    Button btnNewButton = new Button(parent, SWT.NONE);
		    btnNewButton.setText("Press OK");
		
		
		this.setControl(parent);
		
		
		
	}
	
	
	
	
}
