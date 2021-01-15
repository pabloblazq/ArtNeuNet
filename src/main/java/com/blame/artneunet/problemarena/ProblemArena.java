package com.blame.artneunet.problemarena;

import java.util.Random;

import com.blame.artneunet.network.Network;

public abstract class ProblemArena {

	protected Integer numProblemIterations;
	protected Network network;
	
	protected Random random;

	public void initialize(Network network, int numProblemIterations) {
		this.network = network;
		this.numProblemIterations = numProblemIterations;
		
		random = new Random();
	}
	
	protected abstract void loadProblemStatusIntoInputLayer();

	protected abstract void processOutputLayer();

	protected abstract void processProblemStep();
	
	protected abstract float calculateResultValue();

	public void processProblem() {
		for(int iter = 0; iter < numProblemIterations; iter++) {
			loadProblemStatusIntoInputLayer();
			
			network.processNetwork();
			
			processOutputLayer();
			
			processProblemStep();
		}
	}

	
}
