package com.blame.artneunet.problemarena;

import java.text.DecimalFormat;

public class Point {

	protected static final DecimalFormat DF = new DecimalFormat();
	static {
		DF.setMaximumFractionDigits(2);
	}
	
	protected float x;
	protected float y;
	
	public Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public static Point calculatePoint(Point point, float deltaX, float deltaY) {
		return new Point(point.x + deltaX, point.y + deltaY);
	}

	public static float calculateDistance(Point netEntityPosition, Point targetPosition) {
		double deltaX = (double) (netEntityPosition.getX() - targetPosition.getX());
		double deltaY = (double) (netEntityPosition.getY() - targetPosition.getY());
		
		return (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	
	
}
