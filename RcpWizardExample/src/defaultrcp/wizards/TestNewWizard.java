package defaultrcp.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class TestNewWizard extends Wizard implements INewWizard {

	public TestNewWizard(){
		
	}
	
	public void addPages(){
		
	}
	
	public boolean canFinish(){
		
		return false;		
	}
	
	@Override
	public boolean performFinish() {
		
		return false;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
				
	}
}
