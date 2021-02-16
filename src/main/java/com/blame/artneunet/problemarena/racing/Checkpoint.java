package com.blame.artneunet.problemarena.racing;

import java.util.Objects;

import com.blame.artneunet.problemarena.common.Point;

public class Checkpoint {

	protected Point pointA;
	protected Point pointB;
	private Integer index;
	
	public Checkpoint(Point pointA, Point pointB, int index) {
		super();
		this.pointA = pointA;
		this.pointB = pointB;
		this.index = index;
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
			return intersection.isInsideSegment(pointA, pointB) && intersection.isInsideSegment(moveFrom, moveTo);
		}
	}
	
	@Override
	public String toString() {
		return "[" + index + ": " + pointA + ", " + pointB + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(pointA, pointB);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Checkpoint other = (Checkpoint) obj;
		return Objects.equals(pointA, other.pointA) && Objects.equals(pointB, other.pointB);
	}

}
