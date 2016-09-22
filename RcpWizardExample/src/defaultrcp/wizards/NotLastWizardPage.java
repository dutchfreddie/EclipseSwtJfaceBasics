package defaultrcp.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class NotLastWizardPage extends WizardPage {

	protected NotLastWizardPage(String pageName) {
		super(pageName);
	}

	protected NotLastWizardPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}
	
	@Override
	public void createControl(Composite parent) {
	}
	
	public boolean canFlipToNextPage(){
		return true;		
	}
	
	public IWizardPage getNextPage(){
		
		return null;		
	}

}
