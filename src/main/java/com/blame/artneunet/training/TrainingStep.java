package com.blame.artneunet.training;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.problemarena.ProblemArena;

public class TrainingStep {

	private static final Logger logger = LogManager.getLogger(Training.class);

	private static final int NUM_ARENA_ITERATIONS = 10;
	
	protected List<Network> networkList;
	protected Class<? extends ProblemArena> problemArenaClass;
	protected int numWinnerNetworks;

	protected Map<Network, List<Float>> resultValuesListByNetwork;

	public TrainingStep(List<Network> networkList, Class<? extends ProblemArena> problemArenaClass, int numWinnerNetworks) {
		super();
		this.networkList = networkList;
		this.problemArenaClass = problemArenaClass;
		this.numWinnerNetworks = numWinnerNetworks;
		
		resultValuesListByNetwork = new HashMap<>();
	}

	public List<Network> processTrainingStep(Class<? extends ProblemArena> problemArenaClass) {
		
		for(int arenaIteration = 0; arenaIteration < NUM_ARENA_ITERATIONS; arenaIteration++) {
			ProblemArena problemArena = getProblemArenaInstance(problemArenaClass);
			processProblemArena(problemArena);
		}
		
		// find the n networks having the lowest average result value
		return findNetworksWithLowestResultValue();
	}

	protected ProblemArena getProblemArenaInstance(Class<? extends ProblemArena> problemArenaClass) {
		try {
			return problemArenaClass.getConstructor(List.class).newInstance(networkList);
		} catch (ReflectiveOperationException e) {
			logger.catching(e);
			return null;
		}
	}
	
	protected void processProblemArena(ProblemArena problemArena) {
		
		Map<Network, Float> resultValuesByNetwork = problemArena.processProblem();
		for(Entry<Network, Float> entry : resultValuesByNetwork.entrySet()) {
			List<Float> resultValuesList = resultValuesListByNetwork.get(entry.getKey());
			if(resultValuesList == null) {
				resultValuesList = new ArrayList<>();
				resultValuesListByNetwork.put(entry.getKey(), resultValuesList);
			}
			resultValuesList.add(entry.getValue());
		}
	}

	protected List<Network> findNetworksWithLowestResultValue() {
		
		Map<Network, Double> avgResultValueByNetwork = new HashMap<>();
		for(Entry<Network, List<Float>> entry : resultValuesListByNetwork.entrySet()) {
			avgResultValueByNetwork.put(entry.getKey(), entry.getValue().stream().mapToDouble(d -> d).average().orElse(0d));
		}

		List<Entry<Network, Double>> sortedEntriesList = avgResultValueByNetwork.entrySet().stream().sorted(Comparator.comparingDouble(Entry<Network, Double>::getValue)).collect(Collectors.toList());
		return sortedEntriesList.stream().limit(numWinnerNetworks).map(Entry<Network, Double>::getKey).collect(Collectors.toList());
	}

}