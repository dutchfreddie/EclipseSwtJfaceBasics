package swt.graphics.paint;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

public class Program {

	public static void main(String[] args) {
		
		int cH = 400;
		int cW = 400;		
		int diameter = 200;	
		int radius = diameter/2;
		
		int xCenterCircle = cW/2;
		int yCenterCircle = cH/2;
		
		int x = xCenterCircle-diameter/2;
		int y = yCenterCircle-diameter/2;
		int delta = 4;
		
		for(int i=0;i<delta;i++){
			int startAngle = 360/delta*i;
			int endAngle = (i+1)*360/delta;			
			int averageAngle =(startAngle+endAngle)/2;
			System.out.println("Start angle: " + startAngle + "; End Angle: " + endAngle);
			System.out.println("Average angle: " + averageAngle);
			int xOnCircle = (int) GeometryUtil.coordinateXonCircle(-averageAngle, radius, xCenterCircle);
			int yOnCircle = (int) GeometryUtil.coordinateYonCircle(-averageAngle, radius, yCenterCircle);
			
			System.out.println(xOnCircle +  " - " + yOnCircle);
			System.out.println("*************************");
			
			
			
		}
		
		
		
		

	}

}
