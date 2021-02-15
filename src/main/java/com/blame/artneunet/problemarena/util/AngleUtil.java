package com.blame.artneunet.problemarena.util;

import com.blame.artneunet.problemarena.common.Point;

public class AngleUtil {

	public static final double PIx2 = Math.PI*2d; // 2*PI :: 360deg
	public static final double PI_2 = Math.PI/2d; // PI/2 ::  90deg
	public static final double PI_4 = Math.PI/4d; // PI/4 ::  45deg
	
	public static double sumAngles(double baseAngle, double deltaAngle) {
		
		double result = baseAngle + deltaAngle;
		if(result > PIx2) {
			return result - PIx2;
		} else if(result < 0d) {
			return result + PIx2;
		} else {
			return result;
		}
	}

	public static Point getTargetPoint(Point position, double distance, double heading) {
		
		double targetX = position.getX() + (distance * Math.sin(heading));
		double targetY = position.getY() + (distance * Math.cos(heading));
		
		return new Point(targetX, targetY);
	}
	
	public static double calculateRotationAngle(Point pointFrom, Point pointTo) {

		double dotProduct = (pointFrom.getX() * pointTo.getX()) + (pointFrom.getY() * pointTo.getY());
		double fromMagnitude = Math.sqrt(pointFrom.getX() * pointFrom.getX()) + (pointFrom.getY() * pointFrom.getY());
		double toMagnitude = Math.sqrt(pointTo.getX() * pointTo.getX()) + (pointTo.getY() * pointTo.getY());
		
		double cosine = dotProduct / (fromMagnitude * toMagnitude);
		return Math.acos(cosine);
	}
}
