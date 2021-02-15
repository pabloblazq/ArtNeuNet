package com.blame.artneunet.problemarena.racing;

import static com.blame.artneunet.problemarena.util.AngleUtil.PI_2;
import static com.blame.artneunet.problemarena.util.AngleUtil.PI_4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.problemarena.common.ColorMap;
import com.blame.artneunet.problemarena.common.Point;
import com.blame.artneunet.problemarena.util.AngleUtil;

public class Racer {

	protected static final double REWARD_FOR_CHECKPOINT = 1000d;
	
	protected Network network;
	protected Point position;
	
	protected double speed;   // in pixels/step (this means that updating the speed from one step to the next means that delta-speed is equiv to distance) 
	protected double heading; // in radians
	
	protected List<Point> positionHistory;
	protected List<Checkpoint> crossedCheckpointList;

	public Racer(Network network, Point position) {
		super();
		this.network = network;
		this.position = position;
		
		this.speed = 0d;
		this.heading = 0d;
		
		this.positionHistory = new ArrayList<>();
		this.crossedCheckpointList = new ArrayList<>();
	}

	
	public Network getNetwork() {
		return network;
	}

	public Point getPosition() {
		return position;
	}

	public double getSpeed() {
		return speed;
	}

	public double getHeading() {
		return heading;
	}
	
	protected double allowedDeltaHeading(double deltaHeading) {
		double allowed = PI_2 / Math.abs(speed); // 90deg * (1/speed)
		if(Math.abs(deltaHeading) < allowed) {
			return deltaHeading;
		} else {
			return allowed * Math.signum(deltaHeading);
		}
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
		for(double distance = 1d; distance < 1000d; distance++) {
			Point targetPoint = AngleUtil.getTargetPoint(position, distance, sensorHeading);
			if(!isInsideTrack(colorMap, targetPoint)) {
				return distance;
			}
		}
		
		return 1000d;
	}

	protected boolean isInsideTrack(ColorMap colorMap, Point targetPoint) {
		Color targetColor = colorMap.getColor((int) Math.round(targetPoint.getY()), (int) Math.round(targetPoint.getX()));
		return targetColor != null && (targetColor.equals(RacingCircuit.COLOR_TRACK) || targetColor.equals(RacingCircuit.COLOR_START_POINT));
	}

	public void updateNetworkInput(ColorMap colorMap) {
		network.setInputLayerValues(Arrays.asList(
				speed,
				heading,
				distanceToOutTrackSensorM90(colorMap),
				distanceToOutTrackSensorM45(colorMap),
				distanceToOutTrackSensor0(colorMap),
				distanceToOutTrackSensorP45(colorMap),
				distanceToOutTrackSensorP90(colorMap)));
	}

	public void updateStatusWithOutputLayer(RacingCircuit racingCircuit) {
		// process output layer
		List<Double> outputLayerValues = network.getOutputLayerValues();
		double deltaSpeed = outputLayerValues.get(0);
		double deltaHeading = allowedDeltaHeading(outputLayerValues.get(1) * PI_2);
		
		// update speed and heading
		speed += deltaSpeed;
		heading = AngleUtil.sumAngles(heading, deltaHeading);
		
		// move to the next position 
		Point previousPosition = position;
		position = AngleUtil.getTargetPoint(position, speed, heading);
		
		// check if any checkpoint was crossed
		Checkpoint crossedCheckpoint = racingCircuit.findCrossedCheckpoint(previousPosition, position);
		if(crossedCheckpoint != null) {
			crossedCheckpointList.add(crossedCheckpoint);
		}
		
		// check if the new position is out of track
		if(!isInsideTrack(racingCircuit.getColorMap(), position)) {
			network.setEnabled(false);
		}
	}

	public void storeHistory() {
		positionHistory.add(position);
	}
	
	public List<Point> getPositionHistory() {
		return positionHistory;
	}

	public double calculateResultValue(RacingCircuit racingCircuit) {
		// if no checkpoint was crossed, then the distance to the startPoint determines the result value
		if(crossedCheckpointList.isEmpty()) {
			return Point.distance(racingCircuit.getStartPoint(), position);
		} else {
			double resultValue = crossedCheckpointList.size() * REWARD_FOR_CHECKPOINT;
			Checkpoint lastCheckpoint = crossedCheckpointList.get(crossedCheckpointList.size()-1);
			return resultValue + Point.distance(lastCheckpoint.getMediumPoint(), position);
		}
	}
}
