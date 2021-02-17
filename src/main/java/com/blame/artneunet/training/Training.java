package com.blame.artneunet.training;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.NetworkBuilder;
import com.blame.artneunet.problemarena.ProblemArena;

public class Training {

	private static final Logger logger = LogManager.getLogger(Training.class);
	
	protected static final int TRAINING_STEPS = 100;

	protected static final int INITIAL_POPULATION = 100;
	protected static final int STEP_NEW_RANDOM_NETWORKS = 10;
	protected static final int NUM_MUTATED_FROM_WINNER_NETWORK = 20;
	protected static final int NUM_WINNER_NETWORKS = 5;
	
	protected NetworkBuilder networkBuilder;
	protected Class<? extends ProblemArena> problemArenaClass;
	
	public Training(NetworkBuilder networkBuilder, Class<? extends ProblemArena> problemArenaClass) {
		this.networkBuilder = networkBuilder;
		this.problemArenaClass = problemArenaClass;
	}

	public void processTraining() {
		logTrainingStart();
		// initial network build
		List<Network> networkList = networkBuilder.buildNetworks(INITIAL_POPULATION, true);
		List<Network> finalWinnerNetworks = null;
		
		// iterate the training steps
		for(int trainingStepIteration = 0; trainingStepIteration < TRAINING_STEPS; trainingStepIteration++) {
			logger.info("Processing training step {}", trainingStepIteration);
			// process the training step
			TrainingStep trainingStep = new TrainingStep(networkList, problemArenaClass, NUM_WINNER_NETWORKS);
			List<Network> winnerNetworks = trainingStep.processTrainingStep();
			ProblemArena problemArena = trainingStep.getSampleProblemArena();
			
			// display condition
			if((trainingStepIteration % 10) == 0 || trainingStepIteration == TRAINING_STEPS -1) {
				problemArena.display(winnerNetworks);
			}
			
			// last training step
			if(trainingStepIteration == TRAINING_STEPS -1) {
				finalWinnerNetworks = winnerNetworks;
			} else {
				// build the next generation: original winner networks, original mutated, new random networks (10/100/10)
				networkList.clear();
				networkList.addAll(winnerNetworks);
				winnerNetworks.stream().forEach(network -> network.setEnabled(true));
				networkList.addAll(generateMutatedNetworks(winnerNetworks));
				networkList.addAll(networkBuilder.buildNetworks(STEP_NEW_RANDOM_NETWORKS, true));
			}
		}
		
		logFinalWinnerNetworks(finalWinnerNetworks);
	}

	protected void logTrainingStart() {
		logger.info("Starting training for:");
		logger.info("  Problem arena: {}", problemArenaClass.getSimpleName());
		logger.info("  Network population: {} -> w:{} m:{} r:{}", INITIAL_POPULATION, NUM_WINNER_NETWORKS, NUM_MUTATED_FROM_WINNER_NETWORK, STEP_NEW_RANDOM_NETWORKS);
		logger.info("  Network structure: {}||{}||{}", networkBuilder.getNumberOfInputNeurons(), 
				networkBuilder.getNumberOfProcessingNeurons().stream().map(i -> i.toString()).collect(Collectors.joining("|")), networkBuilder.getNumberOfOutputNeurons());
	}

	protected void logFinalWinnerNetworks(List<Network> finalWinnerNetworks) {
		logger.info("Final winner networks genealogy:");
		for(Network network : finalWinnerNetworks) {
			logger.info("  {}", network.getGenealogyItemList());
		}
	}

	protected List<Network> generateMutatedNetworks(List<Network> winnerNetworks) {
		
		List<Network> mutatedNetworks = new ArrayList<>();
		for(Network winnerNetwork : winnerNetworks) {
			mutatedNetworks.addAll(generateMutatedNetworks(winnerNetwork));
		}

		return mutatedNetworks;
	}

	protected List<Network> generateMutatedNetworks(Network winnerNetwork) {
		
		List<Network> mutatedNetworks = new ArrayList<>();
		for(int i = 0; i < NUM_MUTATED_FROM_WINNER_NETWORK; i++) {
			mutatedNetworks.add(networkBuilder.generateMutatedNetwork(winnerNetwork));
		}

		return mutatedNetworks;
	}
}
