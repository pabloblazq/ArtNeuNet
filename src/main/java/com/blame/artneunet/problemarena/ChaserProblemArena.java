package com.blame.artneunet.problemarena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.blame.artneunet.network.Network;

/**
 * i1: target x
 * i2: target y
 * i3: net-entity x
 * i4: net-entity y
 * o1: net-entity delta-x
 * o2: net-entity delta-y
 */
public class ChaserProblemArena extends ProblemArena {

	protected final float sizeX;
	protected final float sizeY;
	
	protected Point targetPosition;
	protected Point nextTargetPosition;
	protected Point netEntityPosition;
	protected Point nextNetEntityPosition;
	
	protected int targetDirectionMaxIters;
	protected int targetDirectionIter;
	protected Point targetTempDestination;
	
	protected List<List<Point>> positions;
	protected float resultValue;
	
	public ChaserProblemArena(Network network, float sizeX, float sizeY, int numProblemIterations) {
		
		initialize(network, numProblemIterations);
		
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		targetPosition = new Point(random.nextFloat() * sizeX, random.nextFloat() * sizeY);
		netEntityPosition = new Point(random.nextFloat() * sizeX, random.nextFloat() * sizeY);
		
		targetDirectionMaxIters = numProblemIterations / 5;
		targetTempDestination = new Point(random.nextFloat() * sizeX, random.nextFloat() * sizeY);

		positions = new ArrayList<>();
		positions.add(Arrays.asList(targetPosition, netEntityPosition, targetTempDestination));
	}

	@Override
	protected void loadProblemStatusIntoInputLayer() {
		
		// do it normalizing to 0 .. 1
		network.setInputLayerValues(Arrays.asList(
				targetPosition.getX() / sizeX,
				targetPosition.getY() / sizeY,
				netEntityPosition.getX() / sizeX,
				netEntityPosition.getY() / sizeY));
	}

	@Override
	protected void processOutputLayer() {
		// get the next net-entity position from the network output layer
		List<Float> outputLayer = network.getOutputLayerValues();
		float netEntityDeltaX = outputLayer.get(0) * 20f;
		float netEntityDeltaY = outputLayer.get(1) * 20f;
		nextNetEntityPosition = Point.calculatePoint(netEntityPosition, netEntityDeltaX, netEntityDeltaY);

		// move towards the target temp destination
		nextTargetPosition = Point.calculatePoint(targetPosition, targetTempDestination, 5f);
		
		//controlPositionLimits();
	}

	protected void controlPositionLimits() {
		if(nextNetEntityPosition.getX() > sizeX - 1) {
			nextNetEntityPosition.setX(sizeX - 1);
		} else if(nextNetEntityPosition.getX() < 0f) {
			nextNetEntityPosition.setX(0f);
		}
		
		if(nextNetEntityPosition.getY() > sizeY - 1) {
			nextNetEntityPosition.setY(sizeY - 1);
		} else if(nextNetEntityPosition.getY() < 0f) {
			nextNetEntityPosition.setY(0f);
		}
		
		if(nextTargetPosition.getX() > sizeX - 1) {
			nextTargetPosition.setX(sizeX - 1);
		} else if(nextTargetPosition.getX() < 0f) {
			nextTargetPosition.setX(0f);
		}
		
		if(nextTargetPosition.getX() > sizeY - 1) {
			nextTargetPosition.setX(sizeY - 1);
		} else if(nextTargetPosition.getY() < 0f) {
			nextTargetPosition.setY(0f);
		}
	}

	@Override
	protected void processProblemStep() {
		targetPosition = nextTargetPosition;
		netEntityPosition = nextNetEntityPosition;
		
		positions.add(Arrays.asList(targetPosition, netEntityPosition, targetTempDestination));
		
		targetDirectionIter++;
		if(targetDirectionIter % targetDirectionMaxIters == 0) {
			targetTempDestination = new Point(random.nextFloat() * sizeX, random.nextFloat() * sizeY);
		}
	}

	public List<List<Point>> getPositions() {
		return positions;
	}

	@Override
	protected float calculateResultValue() {
		
		// calculate the distance between netEntity and target
		resultValue = Point.calculateDistance(netEntityPosition, targetPosition);
		return resultValue;
	}
}
