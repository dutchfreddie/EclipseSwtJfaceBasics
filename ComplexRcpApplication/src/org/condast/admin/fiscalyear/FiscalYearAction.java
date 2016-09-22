package org.condast.admin.fiscalyear;

import java.util.Calendar;

import org.condast.admin.utils.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class FiscalYearAction extends Action implements ISelectionListener,
		IWorkbenchAction {

	public static final String S_NEW_FISCAL_YEAR_ID = "org.condast.admin.fiscalyear.new";

	public static final String S_TEXT = "&Add New Fiscal Year";
	public static final String S_TOOLTIP = "Adds a New fiscal Year to the Admin Tool";
	
	private IWorkbenchWindow window;
	
	public void addNewFiscalYearAction( IWorkbenchWindow window ){
		this.window = window;
		setId( S_NEW_FISCAL_YEAR_ID );
		super.setText( S_TEXT );
		super.setToolTipText( S_TOOLTIP );
		setImageDescriptor( Images.getInstance().getImageDescriptor( Images.S_ERROR_ICON ));
		window.getSelectionService().addSelectionListener( this );
	}
	
	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener( this );

	}

	@Override
	public void selectionChanged(IWorkbenchPart arg0, ISelection incoming) {
		if(!( incoming instanceof IStructuredSelection ))
			return;
		//selection = ( IStructuredSelection )incoming;

	}

	public void run(){
		FiscalYear.createNewFiscalYear( Calendar.getInstance().getTime());
	}
}
