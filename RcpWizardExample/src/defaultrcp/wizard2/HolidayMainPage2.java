/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002 - All Rights Reserved. 
 */
 
package defaultrcp.wizard2;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
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
//import org.eclipse.ui.IWorkbench;

/**
 * Class representing the first page of the wizard
 */

public class HolidayMainPage2 extends WizardPage implements Listener
{

	public static final String copyright = "(c) Copyright IBM Corporation 2002.";	
	
	//IWorkbench workbench;
	IStructuredSelection selection;
	
	// widgets on this page 
	Combo travelDate;
	Combo travelMonth;
	Combo travelYear;
	Combo returnDate;
	Combo returnMonth;
	Combo returnYear;	
	Button priceButton;	
	Text fromText;
	Text toText;
	Button planeButton;
	Button carButton;
	Button trainButton;
	
	// status variable for the possible errors on this page
	// timeStatus holds an error if the return date is before the departure date
	IStatus timeStatus;
	
	// holds an error is the destination and departure fields are the same
	IStatus destinationStatus;

	private Button walkingButton;
		
	final static String[] dates ={ "1", "2", "3", "4", "5", "6", "7", "8", "9",
		"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
		"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	final static String[] months= {"January", "February", "March", "April", "May",
		"June", "July", "August", "September", "October", "November", "December" };

	final static String[] years;
	final static int startingYear;
	
	static {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		startingYear = cal.get(Calendar.YEAR);
		years = new String[3];
		for (int i = -1; i < 2; i++) {
			years[i+1] = String.valueOf(startingYear + i);
		}
	}
	/**
	 * Constructor for HolidayMainPage.
	 */
	public HolidayMainPage2() {
		super("Page1");
		setTitle("Your Holiday");
		setDescription("Select the details of your holiday");
		//this.workbench = workbench;	
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {

	    // create the composite to hold the widgets
		GridData gd;
		Composite composite =  new Composite(parent, SWT.NULL);

	    // create the desired layout for this wizard page
		GridLayout gl = new GridLayout();
		int ncol = 4;
		gl.numColumns = ncol;
		composite.setLayout(gl);

		// initial value for date of travel, today's date
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);

		
	    // create the widgets. If the appearance of the widget is different from the default, 
	    // create a GridData for it to set the alignment and define how much space it will occupy		
	    	    
	    // Date of travel
		new Label (composite, SWT.NONE).setText("Travel on:");						
		travelDate = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData();
		gd.horizontalAlignment = GridData.BEGINNING;
		travelDate.setLayoutData(gd);
		travelDate.setItems(dates);		
		travelDate.setText(travelDate.getItem(dayOfMonth -1)); // 0 based indexes

		travelMonth = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		travelMonth.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		travelMonth.setItems(months);
		travelMonth.setText(travelMonth.getItem(month));

		travelYear = new Combo(composite,  SWT.BORDER | SWT.READ_ONLY);
		travelYear.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		travelYear.setItems(years);
		travelYear.setText(travelYear.getItem(year - startingYear));


		// Date of return		
		new Label (composite, SWT.NONE).setText("Return on:");		
		gd = new GridData();
		gd.horizontalAlignment = GridData.BEGINNING;	
		returnDate = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		returnDate.setLayoutData(gd);
		returnDate.setItems(dates);

		returnMonth = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		returnMonth.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		returnMonth.setItems(months);

		returnYear = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		returnYear.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		returnYear.setItems(years);

		createLine(composite, ncol);

		// Departure				
		new Label (composite, SWT.NONE).setText("From:");				
		fromText = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		fromText.setLayoutData(gd);
		
		// Destination
		new Label (composite, SWT.NONE).setText("To:");				
		toText = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		toText.setLayoutData(gd);

		createLine(composite, ncol);

		// Choice of transport		
		planeButton = new Button(composite, SWT.RADIO);
		planeButton.setText("Take a plane");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol;
		planeButton.setLayoutData(gd);
		planeButton.setSelection(true);
		
		carButton = new Button(composite, SWT.RADIO);
		carButton.setText("Rent a car");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol;
		carButton.setLayoutData(gd);
		
		trainButton = new Button(composite, SWT.RADIO);
		trainButton.setText("Take the train");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol;
		trainButton.setLayoutData(gd);
		
		walkingButton = new Button(composite, SWT.RADIO);
		walkingButton.setText("Travel by foot");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol;
		walkingButton.setLayoutData(gd);
		
		
	    // set the composite as the control for this page
		setControl(composite);		
		addListeners();
	}
	
	private void addListeners()
	{
		planeButton.addListener(SWT.Selection, this);
		carButton.addListener(SWT.Selection, this);
		trainButton.addListener(SWT.Selection, this);
		walkingButton.addListener(SWT.Selection, this);
		
		fromText.addListener(SWT.KeyUp, this);
		toText.addListener(SWT.KeyUp, this);
		travelDate.addListener(SWT.Selection, this);
		travelMonth.addListener(SWT.Selection, this);
		travelYear.addListener(SWT.Selection, this);
		returnDate.addListener(SWT.Selection, this);
		returnMonth.addListener(SWT.Selection, this);
		returnYear.addListener(SWT.Selection, this);
	}

	
	

	/*
	 * Returns the next page.
	 * Saves the values from this page in the model associated 
	 * with the wizard. Initializes the widgets on the next page.
	 */
	
	public IWizardPage getNextPage()
	{    		
		if (planeButton.getSelection()) {
			PlanePage2 page = ((HolidayWizard2)getWizard()).planePage;
			//page.onEnterPage();
			return page;
		}
		
	    // Returns the next page depending on the selected button
		if (carButton.getSelection()) { 
			CarPage2 page = ((HolidayWizard2)getWizard()).carPage;
			return page;
		}
		
		if(trainButton.getSelection()){
			TrainPage2 page = ((HolidayWizard2)getWizard()).trainPage;
			return page;
		}
		
		if(walkingButton.getSelection()){
			WalkingPage2 page = ((HolidayWizard2)getWizard()).walkingPage;
			return page;
		}
		
		
		
		return null;
	}

	/**
	 * @see IWizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage()
	{
		return true;
		
	}
	
	

	
	
	

	private void createLine(Composite parent, int ncol) 
	{
		Label line = new Label(parent, SWT.SEPARATOR|SWT.HORIZONTAL|SWT.BOLD);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = ncol;
		line.setLayoutData(gridData);
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}	

	

}

