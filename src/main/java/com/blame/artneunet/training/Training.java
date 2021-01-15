package com.blame.artneunet.training;

import java.util.ArrayList;
import java.util.List;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.exception.BuildNetworkException;
import com.blame.artneunet.problemarena.ProblemArena;

public class Training {

	protected static final int DEFAULT_TRAINING_STEPS = 10;
	protected static final int STEP_POPULATION = 100;
	
	protected int trainingSteps;

	protected Network baseNetwork;
	
	/**
	 * 
	 * @param baseNetwork
	 */
	public Training(Network baseNetwork, ProblemArena problemArena) {
		this.baseNetwork = baseNetwork;
		
		trainingSteps = DEFAULT_TRAINING_STEPS;
	}

	/**
	 * 
	 * @param trainingSteps
	 */
	public void setTrainingSteps(int trainingSteps) {
		this.trainingSteps = trainingSteps;
	}
	
	/**
	 * @throws BuildNetworkException 
	 * 
	 */
	public void runTraining() throws BuildNetworkException {
		// initial network build
		List<Network> networkToEvaluateList = new ArrayList<>();
		for(int i = 0; i < STEP_POPULATION; i++) {
			Network network = baseNetwork.cloneNetwork();
			network.randomizeConnections();
			networkToEvaluateList.add(network);
		}
	
		// load the initial situation into the input layer 
		
		// iterate the training steps
		for(int iStep = 0; iStep < trainingSteps; iStep ++) {
			// run all the networks into the toEvaluate list against the problemArena (same problemArena). this means
			// running the same pair network-problemArena until exit condition (evaluate the network during a certain amount of steps into the problemArena)
				// load input layer values with the problemArena situation
				// run the network
				// apply the output layer values into the problemArena, and run the Arena step
			
			// evaluate the output layer of all the networks against the reward function
			
			// build the next generation: original winner networks, original mutated, new random networks (10/80/10 ??)
			
			// load the initial problem situation into the input layer of the next generation
		}
		
	}
}
