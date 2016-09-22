package defaultrcp.holidaywizard_new;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * Wizard page shown when the user has chosen car as means of 
 * transport
 */
public class CarPage extends WizardPage implements Listener {
	public static final String copyright = "(c) Copyright IBM Corporation 2002.";

	private String[] rentalCompanyNames = {"Cheap Cars", "Family Cars", "Luxury Cars"};
	private float[] prices = {90, 120, 150};
	
	private Combo companyCombo;
	private Text priceText;	
	private Button insuranceButton;
		
	/**
	 * Constructor for HolidayDocumentPage.
	 * @param arg0
	 */
	public CarPage(String arg0) {
		super(arg0);
		setTitle("Travel by car");
		setDescription("Specify car choices");
		
	}

	@Override
	public void createControl(Composite parent) 
	{
	    // create the composite to hold the widgets
	    Composite composite = new Composite(parent, SWT.NONE);
	    
	    // create the desired layout for this wizard page
	    GridLayout gl = new GridLayout();
	    int ncol = 2;
	    gl.numColumns = ncol;
	    composite.setLayout(gl);
	    
	    // create the widgets. If the appearance of the widget is different from the default, 
	    // create a GridData for it to set the alignment and define how much space it will occupy
	    
	    // rental company
	    new Label (composite, SWT.NONE).setText("Rental company");
	    companyCombo = new Combo(composite, SWT.BORDER|SWT.READ_ONLY);
	    companyCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    companyCombo.setItems(rentalCompanyNames);
	    companyCombo.addListener(SWT.Selection, this);
	    
	    
	    new Label (composite, SWT.NONE).setText("Price:");
	    priceText = new Text(composite, SWT.BORDER|SWT.MULTI);
	    priceText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    priceText.setEditable(false);
	    
	    // insurance button
	    insuranceButton = new Button(composite, SWT.CHECK);
	    insuranceButton.setText("Buy insurance");
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    gd.horizontalSpan = ncol;
		insuranceButton.setLayoutData(gd);
		insuranceButton.setSelection(true);
	    // set the composite as the control for this page
	    setControl(composite);
	}

	
   @Override
	public void handleEvent(Event e)
	{
		if (e.widget == companyCombo) {
			if (companyCombo.getSelectionIndex() >=0)
				priceText.setText("£"+prices[companyCombo.getSelectionIndex()]);
		}
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();		
	}
	

	
   @Override
   public boolean canFlipToNextPage()
	{
		// no next page for this path through the wizard
		return false;
	}

	@Override
   public boolean isPageComplete()
	{
		HolidayWizard wizard = (HolidayWizard)getWizard();
		if (companyCombo.getText().length() == 0) {
			wizard.setCarCompleted(false);
			return false;
		}
		saveDataToModel();
		return true;
	}
	
	private void saveDataToModel()
	{
		HolidayWizard wizard = (HolidayWizard)getWizard();
		wizard.getModel().setRentalCompany(companyCombo.getText());
		wizard.getModel().setCarPrice(priceText.getText());
		wizard.getModel().setBuyInsurance(insuranceButton.getSelection());
		wizard.setCarCompleted(true);
	}
}
