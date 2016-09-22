package defaultrcp.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SecondWizardPage extends WizardPage{

	public SecondWizardPage(String pageName, String title){
		super(pageName);
		this.setTitle(title);
	}
	
	protected SecondWizardPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		AbstractComposite composite = new AbstractComposite(parent, SWT.NONE);
		this.setControl(composite);
		
	}

}
