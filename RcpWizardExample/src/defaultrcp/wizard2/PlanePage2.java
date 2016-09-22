/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002 - All Rights Reserved. 
 */

package defaultrcp.wizard2;

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

public class PlanePage2 extends WizardPage {
	public static final String copyright = "(c) Copyright IBM Corporation 2002.";
	
	// widgets on this page
	List flightsList;
	Combo seatCombo;	
	Button priceButton;

	final static float standardPrice = 100;
	final static String[] seatChoices = {"Window", "Aisle", "Center"};
	final static double discountRate = 0.9;

	float price = standardPrice;
		
	/**
	 * Constructor for PlanePage.
	 */
	protected PlanePage2(String arg0) {
		super(arg0);
		setTitle("Travel by plane");
		setDescription("Specify flight and seat choice");
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
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
		gd = new GridData();
		gd.horizontalAlignment =GridData.END;
		priceButton.setLayoutData(gd);


		flightsList = new List(composite, SWT.BORDER | SWT.READ_ONLY  );
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan =ncol;
		flightsList.setLayoutData(gd);
		
		// seat choice		
		new Label (composite, SWT.NONE).setText("Seat choice:");
		seatCombo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		seatCombo.setLayoutData(gd);
		seatCombo.setItems(seatChoices);
		seatCombo.setText(seatChoices[0]);
		
	    // set the composite as the control for this page
		setControl(composite);		
		setPageComplete(true);
	}

	public boolean canFlipToNextPage()
	{
		// no next page for this path through the wizard
		return false;
	}
	
    /*
     * Process the events: 
     * when the user has entered all information
     * the wizard can be finished
     */
	
	
	/*
	 * Sets the completed field on the wizard class when all the information 
	 * is entered and the wizard can be completed
	 */	 
	public boolean isPageComplete()
	{
		
		return true;
	}
	
	

	void onEnterPage()
	{
		/*
	    // Gets the model
	    HolidayWizard2 wizard = (HolidayWizard2)getWizard();
		
		// arbitrary values
		String text1 = data +" price �400 - British Airways";
		String text2 = data +" price �500 - Air France";
		if (model.resetFlights) {
			wizard.planeCompleted = false;	
			flightsList.removeAll();
			flightsList.add(text1);
			flightsList.add(text2);	
		}
		*/
	}

	
}
