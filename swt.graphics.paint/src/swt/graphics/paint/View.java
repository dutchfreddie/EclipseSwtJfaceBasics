package swt.graphics.paint;



import org.eclipse.jface.util.Geometry;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "swt.graphics.paint.view";
	private Canvas canvas;
	
	private static final RGB[] COLORS = new RGB[] {
	    new RGB( 21,184,185 ),//groen-blauw
	    new RGB( 102,169,58 ), //groen
	    new RGB( 71,110,188 ), //marien blauw
	    new RGB( 251,113,189 ), //pink
	    new RGB( 144,202,215 ), // licht blauw-grijzig
	    new RGB( 254,207,21 ), //vuil-geel
	    new RGB( 255,83,22 ), // vuil-orangje
	    new RGB( 182,199,66 ), // vuil-licht groen
	    new RGB( 254,159,169 ),// vuil-roze
	    new RGB( 159,122,171 ),//vuil-licht paars
	    new RGB( 66,187,134 )// vuil-groen
	  };

	
	public void createPartControl(Composite parent) {
		
		GridLayout gl = new GridLayout(2,true);
		gl.marginHeight=0;
		gl.marginWidth=0;		
		gl.horizontalSpacing=0;
		gl.verticalSpacing=0;
		
		parent.setLayout(gl);
		GridData gd = new GridData(SWT.FILL,SWT.FILL,true,true,1,1);
		parent.setLayoutData(gd);	
		
		Canvas canvas = new Canvas(parent, SWT.BORDER);
		canvas.setBackground(new Color( canvas.getDisplay(), COLORS[4]));
		canvas.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		DrawingAreaPaintListener paintListener = new DrawingAreaPaintListener(canvas);
		canvas.addPaintListener(paintListener);	
		
		Canvas canvas2 = new Canvas(parent, SWT.BORDER);
		canvas2.setBackground(new Color( canvas2.getDisplay(), COLORS[7]));
		canvas2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		DrawingAreaPaintListener paintListener2 = new DrawingAreaPaintListener(canvas2);
		canvas2.addPaintListener(paintListener2);	
		
		Canvas canvas3 = new Canvas(parent, SWT.BORDER);
		canvas3.setBackground(new Color( canvas3.getDisplay(), COLORS[3]));
		canvas3.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		DrawingAreaPaintListener paintListener3 = new DrawingAreaPaintListener(canvas3);
		canvas3.addPaintListener(paintListener3);	
		
		Canvas canvas4 = new Canvas(parent, SWT.BORDER);
		canvas4.setBackground(new Color( canvas4.getDisplay(), COLORS[5]));
		canvas4.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		DrawingAreaPaintListener paintListener4 = new DrawingAreaPaintListener(canvas4);
		canvas4.addPaintListener(paintListener4);	
	}

	public void setFocus() {
		
	}
	
	private class DrawingAreaPaintListener implements PaintListener{

		private Canvas canvas;
		
		public DrawingAreaPaintListener(Canvas canvas) {
			this.canvas=canvas;
		}
		@Override
		public void paintControl(PaintEvent event) {
			
			Rectangle clientArea = canvas.getClientArea();
			int cH = clientArea.height;
			int cW = clientArea.width;
			Display display = canvas.getDisplay();
			GC gc = event.gc;
			
			int diameter = 200;			
			int xCenterCircle = cW/2-diameter/2;
			int yCenterCircle = cH/2-diameter/2;
			Color color = display.getSystemColor(SWT.COLOR_RED);
			gc.setBackground(new Color( canvas.getDisplay(), COLORS[1])); 
			gc.fillOval(xCenterCircle, yCenterCircle, diameter, diameter);
						
			gc.setBackground(new Color( canvas.getDisplay(), COLORS[2]));
			gc.fillArc(xCenterCircle, yCenterCircle, diameter, diameter, 0, 45);
			
			
			color = display.getSystemColor(SWT.COLOR_BLACK);
			gc.setBackground(color);
			gc.setLineWidth(5);
			int xOnCircle = (int) GeometryUtil.coordinateXonCircle(-90, 100, cW/2);
			int yOnCircle = (int) GeometryUtil.coordinateYonCircle(-90, 100, cH/2);
			
			//gc.drawLine(cW/2, cH/2, xOnCircle, yOnCircle);
			
			
			//color = display.getSystemColor(SWT.COLOR_WHITE);
			Font font = new Font(display,"Arial",14,SWT.BOLD | SWT.ITALIC);
			gc.setFont(font);
			gc.setBackground(color);
			FontMetrics mf = gc.getFontMetrics();
			int averageS = mf.getAverageCharWidth();
			int averageH = mf.getHeight();
			String s= "Utrecht";
			int sizeS = s.length();
			//gc.drawString("Utrecht", xOnCircle, yOnCircle);
			gc.drawText("Utrecht", xOnCircle-averageS*sizeS, yOnCircle-10, true);
			System.out.println(mf.getAverageCharWidth());
			
			
			
			
			
			
				
			
		}
		
		private void createPiChart(PaintEvent event){
			Rectangle clientArea = canvas.getClientArea();
			int cAheight = clientArea.height;
			int cAwidth = clientArea.width;
			Display display = canvas.getDisplay();
			GC gc = event.gc;
			
			int x = cAheight/4;
			int y = cAwidth/4;
			int width = 200;
			int height = 200;			
			
			for(int i=0;i<3;i++){
				int deltaArc = 360/3;
				int startArc = i*deltaArc;
			
				Color color = null;
				if(i==0)
					color = display.getSystemColor(SWT.COLOR_RED);
				if(i==1)
					color = display.getSystemColor(SWT.COLOR_BLUE);
				if(i==2)
					color = display.getSystemColor(SWT.COLOR_YELLOW);
				gc.setBackground(color);			
				
				gc.fillArc(x, y, width, height, startArc, deltaArc);
			}			
		}
		
	}
	
	
}