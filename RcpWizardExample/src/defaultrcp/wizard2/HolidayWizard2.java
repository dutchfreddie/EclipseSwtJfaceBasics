package defaultrcp.wizard2;

import java.util.List;


//import nl.frederik.wizard.pages.IWorkbenchWizard;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;

public class HolidayWizard2 extends Wizard
{
	public static final String copyright = "(c) Copyright IBM Corporation 2002.";	
	// wizard pages
	HolidayMainPage2 holidayPage;
	PlanePage2 planePage;
	CarPage2   carPage;
	TrainPage2 trainPage;
	WalkingPage2 walkingPage;
	private List<String> list;
	

	public HolidayWizard2() {
		super();		
	}
	
	public HolidayWizard2(List<String> list){
		super();
		this.setList(list);
	}
	
	public void addPages()
	{
		
		holidayPage = new HolidayMainPage2();
		addPage(holidayPage);		
		planePage = new PlanePage2("");
		addPage(planePage);		
		trainPage = new TrainPage2("");
		addPage(trainPage);
		carPage = new CarPage2("");
		addPage(carPage);
		walkingPage = new WalkingPage2("");
		addPage(walkingPage);
		
	}

	

	@Override
	public boolean canFinish()
	{
		return true;
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}

