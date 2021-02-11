package com.blame.artneunet.problemarena.racing;

import static com.blame.artneunet.problemarena.util.AngleUtil.PI_2;
import static com.blame.artneunet.problemarena.util.AngleUtil.PI_4;
import static com.blame.artneunet.problemarena.util.AngleUtil.PIx2;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.problemarena.common.ColorMap;
import com.blame.artneunet.problemarena.common.Point;
import com.blame.artneunet.problemarena.util.AngleUtil;

public class Racer {

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
		return heading %= PIx2;
	}

	protected double allowedDeltaHeading(double deltaHeading) {
		return PI_2 / speed; // 90deg * (1/speed)
	}
	
	public double distanceToOutTrackSensorM90(ColorMap colorMap) {
		return distanceToOutTrack(colorMap, -PI_2);
	}
	public double distanceToOutTrackSensorM45(ColorMap colorMap) {
		return distanceToOutTrack(colorMap, -PI_4);
	}
	public double distanceToOutTrackSensor0(ColorMap colorMap) {
		return distanceToOutTrack(colorMap, 0d);
	}
	public double distanceToOutTrackSensorP45(ColorMap colorMap) {
		return distanceToOutTrack(colorMap, PI_4);
	}
	public double distanceToOutTrackSensorP90(ColorMap colorMap) {
		return distanceToOutTrack(colorMap, PI_2);
	}

	protected double distanceToOutTrack(ColorMap colorMap, double sensorDeltaHeading) {
		
		double sensorHeading = AngleUtil.sumAngles(heading, sensorDeltaHeading);
		
		
		// TODO Auto-generated method stub
		return 0;
	}
}
