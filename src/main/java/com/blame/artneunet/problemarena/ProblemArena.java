package com.blame.artneunet.problemarena;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.blame.artneunet.network.Network;

public abstract class ProblemArena {

	protected List<Network> networkList;
	protected int numProblemIterations;
	
	protected Random random;

	public ProblemArena(List<Network> networkList, int numProblemIterations) {
		this.networkList = networkList;
		this.numProblemIterations = numProblemIterations;

		random = new Random();
	}

	protected abstract void loadProblemStatusIntoInputLayer();

	protected abstract void processProblemStep();
	
	protected abstract Map<Network, Float> calculateResultValues();

	public Map<Network, Float> processProblem() {
		for(int iter = 0; iter < numProblemIterations; iter++) {
			loadProblemStatusIntoInputLayer();
			
			processNetworks();
			
			processProblemStep();
		}
		
		return calculateResultValues();
	}

	protected void processNetworks() {
		for(Network network : networkList) {
			network.processNetwork();
		}
	}

	public Integer getNumProblemIterations() {
		return numProblemIterations;
	}

}
