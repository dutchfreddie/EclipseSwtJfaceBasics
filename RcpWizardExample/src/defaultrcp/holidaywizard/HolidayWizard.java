package defaultrcp.holidaywizard;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Wizard class
 */
public class HolidayWizard extends Wizard implements INewWizard
{
	public static final String copyright = "(c) Copyright IBM Corporation 2002.";	
	// wizard pages
	HolidayMainPage holidayPage;
	PlanePage planePage;
	CarPage   carPage;
	
	HolidayModel model;
	
	protected IStructuredSelection selection;
	
	protected boolean planeCompleted = false;
	protected boolean carCompleted = false;
	
	protected IWorkbench workbench;

	public HolidayWizard() {
		super();
		model = new HolidayModel();
	}
	
	public void addPages()
	{
		holidayPage = new HolidayMainPage(workbench, selection);
		addPage(holidayPage);
		planePage = new PlanePage("");
		addPage(planePage);
		carPage = new CarPage("");
		addPage(carPage);
	}

		public void init(IWorkbench workbench, IStructuredSelection selection) 
	{
		this.workbench = workbench;
		this.selection = selection;
		
	}

	
	public boolean canFinish()
	{ 
		// cannot complete the wizard from the first page
		if (this.getContainer().getCurrentPage() == holidayPage) 
			return false;
		// based on the type of transport return the right flag			
		if (model.usePlane) 
			return planeCompleted;
		
		return carCompleted;		
	}
	
	public boolean performFinish() 
	{
		String summary = model.toString();
		MessageDialog.openInformation(workbench.getActiveWorkbenchWindow().getShell(), 
			"Holiday info", summary);
		return true;
	}
}

