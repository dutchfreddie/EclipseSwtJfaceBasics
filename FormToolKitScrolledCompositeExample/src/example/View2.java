package example;

import java.awt.Color;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

public class View2 extends ViewPart {

	private Label label;
	private  Text text;
	private GridData gridData;
	private Button b1,b2;
	
	@Override
	public void createPartControl(Composite parent) {
		
		/*
		ScrolledComposite g = createScrolledComposite(parent,2,"Test");
		for(int i=0;i<20;i++){
			label = new Label(g,SWT.LEFT);
			label.setText("Addition " + (i+1));
			text = new Text(g,SWT.SINGLE|SWT.BORDER);
			g.setContent(label);g.setContent(text);
			gridData = new GridData();
			gridData.horizontalAlignment=GridData.FILL;
			gridData.grabExcessHorizontalSpace=false;
			text.setLayoutData(gridData);
		}
		
		ScrolledComposite c1 = new ScrolledComposite(parent, SWT.BORDER
		        | SWT.H_SCROLL | SWT.V_SCROLL);
				
		b1 = new Button(c1, SWT.PUSH);
		b1.setText("fixed size button");
		b1.setSize(400, 400);
		c1.setContent(b1);
		b1 = new Button(c1, SWT.PUSH);
		b1.setText("fixed size button 2");
		b1.setSize(400, 400);
		c1.setContent(b1);
		*/
		createProjectPane(parent);
		
		
		
		
		
		
		

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	private Group createGroup(Composite parent, int numOfColumns, String title){
		Group g = new Group(parent, SWT.BORDER);
		g.setText(title);
		GridLayout layout = new GridLayout();
		layout.numColumns=numOfColumns;
		g.setLayout(layout);		
		
		return g;
	}

	private Composite createComposite(Composite parent, int numOfColumns, String title){
		Composite g = new Composite(parent, SWT.V_SCROLL|SWT.H_SCROLL);
		GridLayout layout = new GridLayout();
		layout.numColumns=numOfColumns;
		g.setLayout(layout);
	
		
		return g;
	}
	
	private ScrolledComposite createScrolledComposite(Composite parent, int numOfColumns, String title){
		ScrolledComposite g = new ScrolledComposite(parent, SWT.V_SCROLL|SWT.H_SCROLL);
		GridLayout layout = new GridLayout();
		layout.numColumns=numOfColumns;
		g.setLayout(layout);	
		
		
		return g;
	}
	


private void createProjectPane(Composite parent){
	  //Group pane=new Group(parent,SWT.NONE);
	  //pane.setText("Projects");
	  //pane.setLayout(new GridLayout(1,false));
	  //GridData gridData=new GridData(GridData.FILL_HORIZONTAL);
	  //gridData.heightHint=200;
	  //pane.setLayoutData(gridData);
	GridData gridData = new GridData(GridData.FILL_BOTH);
	GridLayout gridLayout = new GridLayout(1, false);
	//gridLayout.numColumns = 1;
	parent.setLayout(gridLayout);		
	FormToolkit formToolkit = new FormToolkit(parent.getDisplay());
	
	Form form = formToolkit.createForm(parent);
	form.setText("Some Crazy Text");
	form.setLayoutData(gridData);
	form.setLayout(new GridLayout());
	form.getBody().setLayout(gridLayout);
	form.getBody().setLayoutData(gridData);
	
	  ScrolledComposite scrolledPane= createScrolledComposite(form.getBody());
	  Composite projectPane = returnComposite(scrolledPane);
	  
	  scrolledPane.setContent(projectPane);
	  scrolledPane.setMinSize(projectPane.computeSize(SWT.DEFAULT,SWT.DEFAULT));
			 
	  Group g1 = returnGroup1(projectPane,2,"Group1");
	  Group g2 = returnGroup1(projectPane,4,"Group2");
	  
	  scrolledPane.setContent(projectPane);
	  scrolledPane.setMinSize(projectPane.computeSize(SWT.DEFAULT,SWT.DEFAULT));
	}

private ScrolledComposite createScrolledComposite(Composite parent){
	 ScrolledComposite scrolledPane=new ScrolledComposite(parent,SWT.BORDER | SWT.V_SCROLL|SWT.H_SCROLL);
	  scrolledPane.setLayoutData(new GridData(GridData.FILL_BOTH));
	  scrolledPane.setExpandVertical(true);
	  scrolledPane.setExpandHorizontal(true);
	  
	  return scrolledPane;
}

private Group returnGroup1(Composite parentPane,int numOfColumns, String title){
	Group g = new Group(parentPane, SWT.NONE);
	g.setLayout(new GridLayout(numOfColumns, false));
	g.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	g.setText(title);
	
	for (  int i = 1;i<10;i++) {
	    
	    Label label=new Label(g,SWT.NONE);
	    label.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
	    label.setFont(new Font(Display.getCurrent(),"Arial",8,SWT.BOLD));
	    label.setText("Schemas: " + i);
	    Label label2 = new Label(g,SWT.RIGHT);
	    label2.setText("Schemas: " + i +"a");
	  }
	
	return g;
	
	
}

private Composite returnComposite(ScrolledComposite scrolledPane){
	Composite projectPane=new Composite(scrolledPane,SWT.NONE);
	  projectPane.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
	  projectPane.setLayout(new GridLayout(1,false));
	  
	  return projectPane;
}
}
