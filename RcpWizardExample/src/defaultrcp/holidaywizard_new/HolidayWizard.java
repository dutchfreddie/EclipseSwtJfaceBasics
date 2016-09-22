package defaultrcp.holidaywizard_new;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Wizard class
 */
public class HolidayWizard extends Wizard implements INewWizard
{
	public static final String copyright = "(c) Copyright IBM Corporation 2002.";	
	// wizard pages
	private HolidayMainPage holidayPage;
	private PlanePage planePage;
	private CarPage   carPage;	
	private HolidayModel model;
	
	protected IStructuredSelection selection;
	
	private boolean planeCompleted = false;
	private boolean carCompleted = false;
	
	private IWorkbench workbench;

	public HolidayWizard() {
		super();
		model = new HolidayModel();
	}
	
	@Override
	public void addPages()
	{
		holidayPage = new HolidayMainPage(workbench, selection);
		addPage(holidayPage);
		planePage = new PlanePage("");
		addPage(planePage);
		carPage = new CarPage("");
		addPage(carPage);
	}

	@Override
		public void init(IWorkbench workbench, IStructuredSelection selection) 
	{
		this.workbench = workbench;
		this.selection = selection;
		
	}

	@Override
	public boolean canFinish()
	{ 
		// cannot complete the wizard from the first page
		if (this.getContainer().getCurrentPage() == holidayPage) 
			return false;
		// based on the type of transport return the right flag			
		if (model.isUsePlane()) 
			return true;
		
		return carCompleted;
		
		
	}
	
	@Override
	public boolean performFinish() 
	{
		String summary = model.toString();
		IWizardPage page = this.getContainer().getCurrentPage();
		Shell shell = Display.getCurrent().getActiveShell();
		MessageDialog.openInformation(shell, "Holiday info", summary);		
		
		return true;
	}

	public HolidayMainPage getHolidayPage() {
		return holidayPage;
	}

	public void setHolidayPage(HolidayMainPage holidayPage) {
		this.holidayPage = holidayPage;
	}

	public PlanePage getPlanePage() {
		return planePage;
	}

	public void setPlanePage(PlanePage planePage) {
		this.planePage = planePage;
	}

	public CarPage getCarPage() {
		return carPage;
	}

	public void setCarPage(CarPage carPage) {
		this.carPage = carPage;
	}

	public HolidayModel getModel() {
		return model;
	}

	public void setModel(HolidayModel model) {
		this.model = model;
	}

	public IStructuredSelection getSelection() {
		return selection;
	}

	public void setSelection(IStructuredSelection selection) {
		this.selection = selection;
	}

	public boolean isPlaneCompleted() {
		return planeCompleted;
	}

	public void setPlaneCompleted(boolean planeCompleted) {
		this.planeCompleted = planeCompleted;
	}

	public boolean isCarCompleted() {
		return carCompleted;
	}

	public void setCarCompleted(boolean carCompleted) {
		this.carCompleted = carCompleted;
	}

	public IWorkbench getWorkbench() {
		return workbench;
	}

	public void setWorkbench(IWorkbench workbench) {
		this.workbench = workbench;
	}

	public static String getCopyright() {
		return copyright;
	}
}

