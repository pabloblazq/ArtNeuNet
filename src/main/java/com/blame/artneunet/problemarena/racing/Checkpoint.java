package com.blame.artneunet.problemarena.racing;

import com.blame.artneunet.problemarena.common.Point;

public class Checkpoint {

	protected Point pointA;
	protected Point pointB;
	
	public Checkpoint(Point pointA, Point pointB) {
		super();
		this.pointA = pointA;
		this.pointB = pointB;
	}

	public Point getMediumPoint() {
		return Point.mediumPoint(pointA, pointB);
	}

	public boolean isCrossedByTransition(Point moveFrom, Point moveTo) {
		// calculate the intersection point for the checkpoint line and the movement line
		Point intersection = Point.intersection(pointA, pointB, moveFrom, moveTo);
		
		// check whether the intersection point is inside the segment represented by the checkpoint
		if(intersection == null) {
			return false;
		} else {
			return intersection.isInsideSegment(pointA, pointB);
		}
	}
	
	@Override
	public String toString() {
		return "[" + pointA + ", " + pointB + "]";
	}

}
