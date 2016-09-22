package defaultrcp.holidaywizard_new;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

/**
 * Wizard page shown when the user has chosen plane as means of 
 * transport
 */

public class PlanePage extends WizardPage implements Listener  {
	public static final String copyright = "(c) Copyright IBM Corporation 2002.";
	
	// widgets on this page
	private List flightsList;
	private Combo seatCombo;	
	private Button priceButton;

	private final static float standardPrice = 100;
	private final static String[] seatChoices = {"Window", "Aisle", "Center"};
	private final static double discountRate = 0.9;

	private float price = standardPrice;
		
	protected PlanePage(String arg0) {
		super(arg0);
		setTitle("Travel by plane");
		setDescription("Specify flight and seat choice");
	}

	@Override
	public void createControl(Composite parent) {

	    // create the composite to hold the widgets
		GridData gd;
		Composite composite = new Composite(parent, SWT.NONE);

	    // create the desired layout for this wizard page
		GridLayout gl = new GridLayout();
		int ncol = 2;
		gl.numColumns = ncol;
		composite.setLayout(gl);
		
	    // create the widgets. If the appearance of the widget is different from the default, 
	    // create a GridData for it to set the alignment and define how much space it will occupy
	    
	    // flights list
		Label label = new Label (composite, SWT.NONE);
		label.setText("Flights:");

		// price button
		priceButton = new Button(composite, SWT.PUSH);
		priceButton.setText("Get price");
		priceButton.addListener(SWT.Selection, this);
		gd = new GridData();
		gd.horizontalAlignment =GridData.END;
		priceButton.setLayoutData(gd);


		flightsList = new List(composite, SWT.BORDER | SWT.READ_ONLY  );
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan =ncol;
		flightsList.setLayoutData(gd);
		flightsList.addListener(SWT.Selection, this);

		// seat choice		
		new Label (composite, SWT.NONE).setText("Seat choice:");
		seatCombo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		seatCombo.setLayoutData(gd);
		seatCombo.setItems(seatChoices);
		seatCombo.setText(seatChoices[0]);
		seatCombo.addListener(SWT.Selection, this);
		
	    // set the composite as the control for this page
		setControl(composite);		
		setPageComplete(true);
	}

	@Override
	public boolean canFlipToNextPage()
	{
		// no next page for this path through the wizard
		return false;
	}
	
	
    @Override
	public void handleEvent(Event e)
	{
		if (e.widget == priceButton) {
			if (flightsList.getSelectionCount() >0) {
				if (((HolidayWizard)getWizard()).getModel().isDiscounted())
					price *= discountRate;
				MessageDialog.openInformation(this.getShell(),"", "Flight price "+ price);
			}
		}
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}
	
	
	@Override
    public boolean isPageComplete()
	{
		HolidayWizard wizard = (HolidayWizard)getWizard();
		if (flightsList.getSelectionCount() == 0) { 
			wizard.setPlaneCompleted(false);
			return false;
		}
		saveDataToModel();
		return true;
	}
	
	private void saveDataToModel()
	{
		HolidayWizard wizard = (HolidayWizard)getWizard();
		wizard.getModel().setSelectedFlight(flightsList.getSelection()[0]);
		wizard.getModel().setSeatChoice(seatCombo.getText());
		wizard.getModel().setPrice(price);
		wizard.setPlaneCompleted(true);
	}	

	public void onEnterPage()
	{
	    // Gets the model
	    HolidayWizard wizard = (HolidayWizard)getWizard();
		HolidayModel model = wizard.getModel();
		
		String data = model.getDeparture()+" to "+model.getDestination()+":";
		// arbitrary values
		String text1 = data +" price £400 - British Airways";
		String text2 = data +" price £500 - Air France";
		if (model.isResetFlights()) {
			wizard.setPlaneCompleted(false);	
			flightsList.removeAll();
			flightsList.add(text1);
			flightsList.add(text2);	
		}
	}
}
