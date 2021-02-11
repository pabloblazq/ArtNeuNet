package com.blame.artneunet.problemarena.common;

import java.text.DecimalFormat;

public class Point {

	protected static final DecimalFormat DF = new DecimalFormat();
	static {
		DF.setMaximumFractionDigits(2);
	}
	
	protected double x;
	protected double y;
	
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public static Point calculatePoint(Point point, double deltaX, double deltaY) {
		return new Point(point.x + deltaX, point.y + deltaY);
	}

	public static Point calculatePoint(Point fromPoint, Point toPoint, double delta) {
		double distance = calculateDistance(fromPoint, toPoint);
		double factor = distance / delta;
		
		double deltaX = (toPoint.getX() - fromPoint.getX()) / factor;
		double deltaY = (toPoint.getY() - fromPoint.getY()) / factor;

		return calculatePoint(fromPoint, deltaX, deltaY);
	}
	
	public static double calculateDistance(Point fromPoint, Point toPoint) {
		double deltaX = fromPoint.getX() - toPoint.getX();
		double deltaY = fromPoint.getY() - toPoint.getY();
		
		return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
