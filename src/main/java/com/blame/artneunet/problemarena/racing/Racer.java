package com.blame.artneunet.problemarena.racing;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.problemarena.common.Point;

public class Racer {

	protected static final double PI_2 = Math.PI/2d; // PI/2 :: 90deg
	
	protected Network network;
	protected Point position;
	
	protected double speed;
	protected double heading; // in radians
	
	public Racer(Network network, Point position) {
		super();
		this.network = network;
		this.position = position;
		
		this.speed = 0d;
		this.heading = 0d;
	}

	public double getSpeed() {
		return speed;
	}

	public double getHeading() {
		return heading;
	}
	
	public double updateSpeed(double deltaSpeed) {
		return speed += deltaSpeed;
	}
	
	public double updateHeading(double deltaHeading) {
		heading += allowedDeltaHeading(deltaHeading);
		return heading %= 360;
	}

	protected double allowedDeltaHeading(double deltaHeading) {
		return PI_2 / speed; // 90deg * (1/speed)
	}
}
