package org.condast.admin;

import org.condast.admin.fiscalyear.FiscalYearAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction newFiscalYearAction;
	private IWorkbenchAction exitAction;
	private IWorkbenchAction helpAction;
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		exitAction = ActionFactory.QUIT.create(window);
		register( exitAction );
		helpAction = ActionFactory.ABOUT.create(window);
		register( helpAction );
		newFiscalYearAction = new FiscalYearAction();
		register( this.newFiscalYearAction );
		super.makeActions(window);
	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager adminMenu = new MenuManager( "&CondastAdmin", "CondastAdmin");
		adminMenu.add( exitAction );
		MenuManager helpMenu = new MenuManager( "&Help", "help");
		helpMenu.add( helpAction );
		menuBar.add( adminMenu );
		adminMenu.add(this.newFiscalYearAction );
		adminMenu.add( helpMenu );
		super.fillMenuBar(menuBar);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolBar = new ToolBarManager( coolBar.getStyle() );
		coolBar.add( toolBar );
		super.fillCoolBar(coolBar);
	}
	
	
}
