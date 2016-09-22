package defaultrcp.views;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import defaultrcp.holidaywizard_new.HolidayWizard;

public class ThirdView extends ViewPart {

	public ThirdView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2,false);
		parent.setLayout(layout);
		
		Text text = new Text(parent,SWT.TOP);
		text.setText("This is Third-View");
		Button btn = new Button(parent,SWT.PUSH);
		btn.setText("Get HolidayWizard");
		btn.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				WizardDialog dialog = new WizardDialog(getSite().getShell(), new HolidayWizard());
				if(dialog.open()==WizardDialog.OK){
					
				}
				else{
					
				}
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
