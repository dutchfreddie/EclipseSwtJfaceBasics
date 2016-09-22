package org.condast.admin.project.newproject;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NewProjectWizardPage extends WizardNewProjectCreationPage {

	public static final String S_FISCAL_YEAR = "FiscalYear";
	public static final String S_SET_FISCAL_YEAR = "Enter Fiscal Year";
	public static final String S_DESCRIPTION = "Enter the fiscal year for the financial administration";
	
	private Date date;
	
	/**
	 * @wbp.parser.constructor
	 */
	public NewProjectWizardPage() {
		super( S_FISCAL_YEAR );
		setPageComplete(false);
		super.setTitle( S_SET_FISCAL_YEAR );
		super.setDescription( S_DESCRIPTION );
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl( parent);
		Composite container = ( Composite) super.getControl();
		setControl(container);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Label lblFiscalYear = new Label(composite, SWT.NONE);
		lblFiscalYear.setText("Fiscal Year: ");
		
		DateTime dateTime = new DateTime( composite, SWT.BORDER | SWT.DROP_DOWN);
		dateTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis( e.time );
				date = calendar.getTime();
			}
		});
		dateTime.setLayoutData(new RowData(115, SWT.DEFAULT));
		dateTime.setBounds(64, 127, 103, 24);
		this.initComposite();
	}
	
	private void initComposite(){
		Calendar calendar = Calendar.getInstance();
		date = calendar.getTime();		
	}

	public Date getFiscalYear() {
		return date;
	}

	@Override
	public boolean canFlipToNextPage() {
		return ( date != null );
	}	
}
