package com.blame.artneunet.problemarena.util;

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
}
