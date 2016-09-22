package swt.graphics.paint;

public class GeometryUtil {
	
	public static double coordinateXonCircle(double angle, double radius, double originX){
		double radian = angle*Math.PI/180;
		
		return originX +radius*Math.cos(radian);
	}
	
	public static double coordinateYonCircle(double angle, double radius, double originY){
		
		double radian = angle*Math.PI/180;
		
		return originY +radius*Math.sin(radian);
	}
	
	public static boolean isPointWithinCircle(double xPoint,double yPoint, double centerCircleX,double centerCircleY,double radius ){
		boolean isInCircle=false;
		if((Math.pow(xPoint-centerCircleX, 2)+Math.pow(yPoint-centerCircleY,2))<radius*radius){
			isInCircle=true;
		}
		return isInCircle;
	}

}
