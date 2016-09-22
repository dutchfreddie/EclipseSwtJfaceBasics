package de.vogella.rcp.intro.wizards;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.vogella.rcp.intro.wizards.wizard.MyWizard;

public class View extends ViewPart {
	public static final String ID = "de.vogella.rcp.intro.wizards.view";

	

	public void createPartControl(Composite parent) {
		FillLayout layout = new FillLayout();
		parent.setLayout(layout);
		
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Open Wizard");
		button.addSelectionListener(new SelectionAdapter() {
		  @Override
		  public void widgetSelected(SelectionEvent e) {
		    WizardDialog wizardDialog = new WizardDialog(getSite().getShell(),
		      new MyWizard());
		    if (wizardDialog.open() == Window.OK) {
		      System.out.println("Ok pressed");
		    } else {
		      System.out.println("Cancel pressed");
		      }
		  }
		}); 
	}

	
	public void setFocus() {
		
	}
}