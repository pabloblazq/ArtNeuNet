package com.blame.artneunet.problemarena;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.Logger;

import com.blame.artneunet.network.Network;

public abstract class ProblemArena {

	protected Logger logger;

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
	
	protected abstract Map<Network, Double> calculateResultValues();

	public Map<Network, Double> processProblem() {
		for(int iter = 0; iter < numProblemIterations; iter++) {
			logger.debug("Processing problem arena iteration {}", iter);
			loadProblemStatusIntoInputLayer();
			
			processNetworks();
			
			processProblemStep();
		}
		
		return calculateResultValues();
	}

	protected void processNetworks() {
		for(int networkIndex = 0; networkIndex < networkList.size(); networkIndex++) {
			logger.debug("Processing network {}", networkIndex);
			Network network = networkList.get(networkIndex);
			if(network.isEnabled()) {
				network.processNetwork();
			}
		}
	}

	public Integer getNumProblemIterations() {
		return numProblemIterations;
	}

	public abstract void display(List<Network> winnerNetworks);

}
