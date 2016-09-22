package defaultrcp.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;

public class FirstWizardWindowBuilder {
	private static Text text;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
	    Shell parent = new Shell(display);
	    
	    parent.setLayout(new GridLayout(5, false));
	    GridData griddata = new GridData();
	    griddata.horizontalSpan = 5;
	    final Label label = new Label(parent, SWT.NONE);
	    label.setText("A first line of text in this place");
	    label.setLayoutData(griddata);
	    new Label(parent, SWT.NONE);
	    
	    Label lblNewLabel = new Label(parent, SWT.NONE);
	    lblNewLabel.setText("Fill In Some Text");
	    new Label(parent, SWT.NONE);
	    
	    text = new Text(parent, SWT.BORDER);
	    text.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text) e.widget;
		        System.out.println(text.getText());
				
			}
	    	
	    });
	      
	    Button btnNewButton = new Button(parent, SWT.NONE);
	    btnNewButton.setText("Press OK");
	    btnNewButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("In textbox is now: " + text.getText());
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    
	    
	    
	    
	    
	    
	    
	   parent.open();
	    while (!parent.isDisposed()) {
	      if (!display.readAndDispatch())
	        display.sleep();
	    }
	    display.dispose();

	}

}
