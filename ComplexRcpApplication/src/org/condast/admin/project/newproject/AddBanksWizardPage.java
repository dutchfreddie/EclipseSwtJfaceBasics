package org.condast.admin.project.newproject;

import java.util.Collection;
import java.util.TreeSet;

import org.condast.admin.banks.Banks;
import org.condast.admin.banks.Banks.BankNames;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddBanksWizardPage extends WizardPage {

	public static final String S_ADD_BANKS = "AddBanks";
	public static final String S_ADD_BANKS_TITLE = "Add the banks that provide account data";
	public static final String S_DESCRIPTION = "Select the banks that will be added";
	
	private Composite container;
	
	private Collection<BankNames> banknames;

	//public enum Banks{
	/**
	 * @wbp.parser.constructor
	 */
	public AddBanksWizardPage() {
		super( S_ADD_BANKS );
		setPageComplete(false);
		super.setTitle( S_ADD_BANKS_TITLE );
		super.setDescription( S_DESCRIPTION );
	}

	public AddBanksWizardPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite( parent, SWT.NONE );
		setControl(container);
		container.setLayout(new GridLayout(1, false));
		
		this.initComponent();
		
	}
	
	private void initComponent(){
		final Banks banks = Banks.getInstance();
		banknames = new TreeSet<BankNames>();
		for( BankNames bank: BankNames.values() ){
			Button btnCheckButton = new Button(container, SWT.CHECK);
			btnCheckButton.setSelection(banks.isSelected(bank));
			if( banks.isSelected(bank))
				banknames.add(bank);
			btnCheckButton.setData(bank);
			btnCheckButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Button button = ( Button )e.widget;
					BankNames bankName = ( BankNames )button.getData();
					if( button.getSelection())
						banknames.add(bankName);
					else
						banknames.remove(bankName);
				}
			});
			btnCheckButton.setText( bank.toString() );			
		}
	}

	public Collection<BankNames> getBanknames() {
		return banknames;
	}
	
	
}
