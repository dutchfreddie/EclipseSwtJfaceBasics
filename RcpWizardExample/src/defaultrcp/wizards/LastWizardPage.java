package defaultrcp.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class LastWizardPage extends WizardPage {

	protected LastWizardPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
	}
	
	public boolean canFlipToNextPage(){
		return false;		
	}
	
	public boolean isPageCompleted(){
		
		return true;		
	}

}
