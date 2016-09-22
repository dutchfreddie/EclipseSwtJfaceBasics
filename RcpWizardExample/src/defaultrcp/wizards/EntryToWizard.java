package defaultrcp.wizards;




import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class EntryToWizard extends Wizard implements INewWizard {

	private WizardPage firstEntryPage;
	private WizardPage secondEntryPage;
	private WizardPage myPageOne;
	private WizardPage myPageTwo;
	
	public EntryToWizard() {
		
	}

	
	public void addPages(){
		setWindowTitle("Wizard Practice");
		
		firstEntryPage = new FirstWizardPage();
		secondEntryPage = new SecondWizardPage("","SecondEntryPage");
		myPageOne = new MyPageOne();
		myPageTwo = new MyPageTwo();
		super.addPage(firstEntryPage);
		super.addPage(secondEntryPage);
		super.addPage(myPageOne);
		super.addPage(myPageTwo);
		//super.addPages();
		
		
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish() {
		System.out.println("Pressed Finish Button");
		System.out.println(((MyPageOne) myPageOne).getText1());
	    System.out.println(((MyPageTwo) myPageTwo).getText1());
	    
	    
		return true;
	}
	
	public boolean performCancel(){
		super.performCancel();
		System.out.println("Pressed Cancel Button");
		System.out.println();
		
		
		
		return false;
		
	}
}
