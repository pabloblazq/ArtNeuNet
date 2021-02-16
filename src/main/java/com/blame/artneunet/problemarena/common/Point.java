package com.blame.artneunet.problemarena.common;

import java.text.DecimalFormat;
import java.util.Objects;

public class Point {

	protected static final double MIN_INTERSECT_DENOM = Double.parseDouble("0.0000000001");

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
		double distance = distance(fromPoint, toPoint);
		double factor = distance / delta;
		
		double deltaX = (toPoint.getX() - fromPoint.getX()) / factor;
		double deltaY = (toPoint.getY() - fromPoint.getY()) / factor;

		return calculatePoint(fromPoint, deltaX, deltaY);
	}
	
	public static double distance(Point fromPoint, Point toPoint) {
		double deltaX = fromPoint.getX() - toPoint.getX();
		double deltaY = fromPoint.getY() - toPoint.getY();
		
		return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
	}

	public static Point mediumPoint(Point pointA, Point pointB) {
		
		return new Point((pointA.x + pointB.x) / 2d, (pointA.y + pointB.y) / 2d);
	}

	public static Point intersection(Point point1, Point point2, Point point3, Point point4) {
		
		double denom = ((point1.x -point2.x) * (point3.y - point4.y)) - ((point1.y -point2.y) * (point3.x - point4.x));
		if(Math.abs(denom) < MIN_INTERSECT_DENOM) {
			return null;
		}
		
		double numx = ((point1.x * point2.y - point1.y * point2.x) * (point3.x - point4.x)) - 
					  ((point3.x * point4.y - point3.y * point4.x) * (point1.x - point2.x));
		
		double numy = ((point1.x * point2.y - point1.y * point2.x) * (point3.y - point4.y)) - 
				      ((point3.x * point4.y - point3.y * point4.x) * (point1.y - point2.y));
		
		return new Point(numx/denom, numy/denom);
	}

	public boolean isInsideSegment(Point pointA, Point pointB) {
		
		boolean insideX = (x >= pointA.x && x <= pointB.x) || (x >= pointB.x && x <= pointA.x);
		boolean insideY = (y >= pointA.y && x <= pointB.y) || (y >= pointB.y && y <= pointA.y);
		
		return insideX && insideY;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}

}
