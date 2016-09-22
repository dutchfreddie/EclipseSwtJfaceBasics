package org.condast.admin.project.newproject;

import java.net.URI;
import java.util.Date;

import org.condast.admin.banks.Banks;
import org.condast.admin.banks.Banks.BankNames;
import org.condast.admin.log.AdminLog;
import org.condast.admin.project.AdminProjectSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class NewProjectWizard extends Wizard implements INewWizard {

	public static final String S_CREATE_NEW_PROJECT = "Create a new Admin Project";
	public static final String S_NEW_PROJECT = "new Admin Project";
	public static final String S_WIZARD_NAME = "New Condast Admin Project";
	
	private NewProjectWizardPage pageOne;
	private AddBanksWizardPage pageTwo;

	public NewProjectWizard() {
		setWindowTitle( S_WIZARD_NAME);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if( page.equals( pageOne ))
			pageTwo.setPageComplete( true );
		return super.getNextPage(page);
	}

	@Override
	public boolean performFinish() {
		Date year = pageOne.getFiscalYear();
		if( year == null  )
			return false;
		String name = pageOne.getProjectName();
		URI location = null;
		if (!pageOne.useDefaults()) {
			location = pageOne.getLocationURI();
			AdminLog.logInfo( "location: " + location.toString());	
		}

		Banks banks = Banks.getInstance();
		banks.clear();
		for( BankNames bank: pageTwo.getBanknames() )
			banks.addBank(bank);

		AdminProjectSupport.createProject(name, year, location, AdminProjectSupport.createPathsFromString( banks.getSelectedBankNames() ));
		//BasicNewProjectResourceWizard.updatePerspective(_configurationElement);
		 return true;
	}

	@Override
	public void addPages() {
	    super.addPages();
		 
	    pageOne = new NewProjectWizardPage( );
	    pageOne.setTitle( S_NEW_PROJECT );
	    pageOne.setDescription( S_NEW_PROJECT );	 
	    addPage(pageOne);
	    
	    pageTwo = new AddBanksWizardPage();
	    pageTwo.setPageComplete( false);
	    addPage( pageTwo );
	}

	@Override
	public boolean canFinish() {
		return pageTwo.isPageComplete();
	}	
}
