package defaultrcp.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import defaultrcp.wizards.EntryToWizard;

public class EntryWizardHandler extends AbstractHandler {

	private WizardDialog dialog;
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		EntryToWizard wizard = new EntryToWizard();
		dialog = new WizardDialog(window.getShell(), wizard);
		dialog.open();
		
		return null;
	}
}
