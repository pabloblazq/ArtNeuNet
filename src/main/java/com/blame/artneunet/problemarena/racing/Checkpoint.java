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

	public boolean movementCrossedCheckpoint(Point pre, Point pos) {
		// TODO: implement
		return false;
	}

	@Override
	public String toString() {
		return "[" + pointA + ", " + pointB + "]";
	}
}
