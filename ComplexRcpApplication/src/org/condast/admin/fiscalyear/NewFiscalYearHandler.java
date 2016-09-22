package org.condast.admin.fiscalyear;

import java.util.Calendar;

import org.condast.admin.views.FiscalYearView;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public class NewFiscalYearHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		if( window == null )
			return null;
		IWorkbenchPage page = window.getActivePage();
		if( page == null )
			return null;
		try{
			FiscalYear.createNewFiscalYear( Calendar.getInstance().getTime() );
			page.showView( FiscalYearView.ID );
		}
		catch( PartInitException e ){
			
		}
		return null;
	}
}
