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

	protected static final float SIZE_X = 1000f;
	protected static final float SIZE_Y = 1000f;
	
	private Point targetPosition;
	private Point nextTargetPosition;
	private Point netEntityPosition;
	private Point nextNetEntityPosition;
	
	private List<List<Point>> positions;
	private float resultValue;
	
	public ChaserProblemArena(Network network) {
		
		initialize(network, 100);
		
		targetPosition = new Point(random.nextFloat() * SIZE_X, random.nextFloat() * SIZE_Y);
		netEntityPosition = new Point(random.nextFloat() * SIZE_X, random.nextFloat() * SIZE_Y);
		
		positions = new ArrayList<>();
		positions.add(Arrays.asList(targetPosition, netEntityPosition));
	}

	@Override
	protected void loadProblemStatusIntoInputLayer() {
		
		// do it normalizing to 0 .. 1
		network.setInputLayerValues(Arrays.asList(
				targetPosition.getX() / SIZE_X,
				targetPosition.getY() / SIZE_Y,
				netEntityPosition.getX() / SIZE_X,
				netEntityPosition.getY() / SIZE_Y));
	}

	@Override
	protected void processOutputLayer() {
		// get the next net-entity position from the network output layer
		List<Float> outputLayer = network.getOutputLayerValues();
		float netEntityDeltaX = outputLayer.get(0) * 5f;
		float netEntityDeltaY = outputLayer.get(1) * 5f;
		nextNetEntityPosition = Point.calculatePoint(netEntityPosition, netEntityDeltaX, netEntityDeltaY);

		// randomize the target movement (-5 : +5)
		float targetDeltaX = (random.nextFloat() * 10f) - 5f;
		float targetDeltaY = (random.nextFloat() * 10f) - 5f;
		nextTargetPosition = Point.calculatePoint(netEntityPosition, targetDeltaX, targetDeltaY);
		
		controlPositionLimits();
	}

	protected void controlPositionLimits() {
		if(nextNetEntityPosition.getX() > SIZE_X - 1) {
			nextNetEntityPosition.setX(SIZE_X - 1);
		} else if(nextNetEntityPosition.getX() < 0f) {
			nextNetEntityPosition.setX(0f);
		}
		
		if(nextNetEntityPosition.getY() > SIZE_Y - 1) {
			nextNetEntityPosition.setY(SIZE_Y - 1);
		} else if(nextNetEntityPosition.getY() < 0f) {
			nextNetEntityPosition.setY(0f);
		}
		
		if(nextTargetPosition.getX() > SIZE_X - 1) {
			nextTargetPosition.setX(SIZE_X - 1);
		} else if(nextTargetPosition.getX() < 0f) {
			nextTargetPosition.setX(0f);
		}
		
		if(nextTargetPosition.getX() > SIZE_Y - 1) {
			nextTargetPosition.setX(SIZE_Y - 1);
		} else if(nextTargetPosition.getY() < 0f) {
			nextTargetPosition.setY(0f);
		}
	}

	@Override
	protected void processProblemStep() {
		targetPosition = nextTargetPosition;
		netEntityPosition = nextNetEntityPosition;
		
		positions.add(Arrays.asList(targetPosition, netEntityPosition));
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
