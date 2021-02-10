package com.blame.artneunet.problemarena.chaser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blame.artneunet.graphics.chaser.ChaserProblemDisplay;
import com.blame.artneunet.network.Network;
import com.blame.artneunet.problemarena.ProblemArena;
import com.blame.artneunet.problemarena.common.Point;

/**
 * i1: target x
 * i2: target y
 * i3: net-entity x
 * i4: net-entity y
 * o1: net-entity delta-x
 * o2: net-entity delta-y
 */
public class ChaserProblemArena extends ProblemArena {

	public static final int DIMENSION_X = 1000;
	public static final int DIMENSION_Y = 1000;
	
	private static final int NUM_PROBLEM_ITERATIONS = 200;

	protected Point targetPosition;
	protected Point nextTargetPosition;
	protected Map<Network, Point> positionByNetwork;
	
	protected int targetDirectionMaxIters;
	protected int targetDirectionIter;
	protected Point targetTempDestination;
	
	protected List<List<Point>> targetPositionHistory;
	protected List<Map<Network, Point>> positionByNetworkHistory;
	protected float resultValue;
	
	public ChaserProblemArena(List<Network> networkList) {
		super(networkList, NUM_PROBLEM_ITERATIONS);
		
		targetPosition = new Point(random.nextFloat() * DIMENSION_X, random.nextFloat() * DIMENSION_Y);
		
		Point initialNetEntityPosition = new Point(random.nextFloat() * DIMENSION_X, random.nextFloat() * DIMENSION_Y);
		positionByNetwork = new HashMap<>();
		for(Network network : networkList) {
			positionByNetwork.put(network, initialNetEntityPosition);
		}
		
		targetDirectionMaxIters = NUM_PROBLEM_ITERATIONS / 5;
		targetTempDestination = new Point(random.nextFloat() * DIMENSION_X, random.nextFloat() * DIMENSION_Y);

		targetPositionHistory = new ArrayList<>();
		targetPositionHistory.add(Arrays.asList(targetPosition, targetTempDestination));
		positionByNetworkHistory = new ArrayList<>();
		positionByNetworkHistory.add(positionByNetwork);
	}

	@Override
	protected void loadProblemStatusIntoInputLayer() {
		for(Network network : networkList) {
			Point netEntityPosition = positionByNetwork.get(network);
			// do it normalizing to 0 .. 1
			network.setInputLayerValues(Arrays.asList(
					targetPosition.getX() / DIMENSION_X,
					targetPosition.getY() / DIMENSION_Y,
					netEntityPosition.getX() / DIMENSION_X,
					netEntityPosition.getY() / DIMENSION_Y));
		}
	}

	@Override
	protected void processProblemStep() {

		// process the output layer
		Map<Network, Point> nextPositionByNetwork = new HashMap<>();
		for(Network network : networkList) {
			// get the next net-entity position from the network output layer
			List<Float> outputLayer = network.getOutputLayerValues();
			float netEntityDeltaX = outputLayer.get(0) * 20f;
			float netEntityDeltaY = outputLayer.get(1) * 20f;
			Point nextPosition = Point.calculatePoint(positionByNetwork.get(network), netEntityDeltaX, netEntityDeltaY);
			nextPositionByNetwork.put(network, nextPosition);
		}

		// store the history
		targetPositionHistory.add(Arrays.asList(targetPosition, targetTempDestination));
		positionByNetworkHistory.add(positionByNetwork);

		// update the positions
		// move target towards the target temp destination
		targetPosition = Point.calculatePoint(targetPosition, targetTempDestination, 5f);
		positionByNetwork = nextPositionByNetwork;
		
		targetDirectionIter++;
		if(targetDirectionIter % targetDirectionMaxIters == 0) {
			targetTempDestination = new Point(random.nextFloat() * DIMENSION_X, random.nextFloat() * DIMENSION_Y);
		}
	}

	@Override
	protected Map<Network, Float> calculateResultValues() {
		// calculate the distance between netEntity and target
		Map<Network, Float> resultValuesByNetwork = new HashMap<>();
		for(Network network : networkList) {
			resultValuesByNetwork.put(network, Point.calculateDistance(positionByNetwork.get(network), targetPosition));
		}
		return resultValuesByNetwork;
	}

	public List<List<Point>> getTargetPositionHistory() {
		return targetPositionHistory;
	}

	public List<Map<Network, Point>> getPositionByNetworkHistory() {
		return positionByNetworkHistory;
	}

	@Override
	public void display(List<Network> winnerNetworks) {
		ChaserProblemDisplay chaserProblemDisplay = ChaserProblemDisplay.getChaserProblemDisplay();
		chaserProblemDisplay.initializeChaserProblemArena(this, winnerNetworks);
		chaserProblemDisplay.display();
		chaserProblemDisplay.close();
	}
	
}
