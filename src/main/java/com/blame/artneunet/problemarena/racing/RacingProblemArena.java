package com.blame.artneunet.problemarena.racing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.problemarena.ProblemArena;
import com.blame.artneunet.problemarena.common.Point;

/**
 * i1:  racer velocity
 * i2:  racer movement angle (heading)
 * i3a: racer sensor -90
 * i3b: racer sensor -45
 * i3c: racer sensor 0
 * i3d: racer sensor +45
 * i3e: racer sensor +90
 * o1: racer delta-velocity
 * o2: racer delta-heading
 * result: distance travelled from last checkpoint + (1000 * num of checkpoints crossed) 
 */
public class RacingProblemArena extends ProblemArena {

	protected static RacingCircuit racingCircuit = new RacingCircuit();
	protected List<Racer> racerList;
	
	public RacingProblemArena(List<Network> networkList, int numProblemIterations) {
		super(networkList, numProblemIterations);
		
		racerList = new ArrayList<>();
		for(Network network : networkList) {
			racerList.add(new Racer(network, racingCircuit.getStartPoint()));
		}
	}

	@Override
	protected void loadProblemStatusIntoInputLayer() {
		racerList.stream().forEach(racer -> racer.updateNetworkInput(racingCircuit.getColorMap()));
	}

	@Override
	protected void processProblemStep() {
		// store the history
		racerList.stream().forEach(racer -> racer.storeHistory());

		// process the output layer
		racerList.stream().forEach(racer -> racer.updateStatusWithOutputLayer(racingCircuit));
	}

	@Override
	protected Map<Network, Double> calculateResultValues() {

		Map<Network, Double> resultValuesByNetwork = new HashMap<>();
		for(Racer racer : racerList) {
			resultValuesByNetwork.put(racer.getNetwork(), racer.calculateResultValue(racingCircuit));
		}
		return resultValuesByNetwork;
	}

	@Override
	public void display(List<Network> winnerNetworks) {
		// TODO Auto-generated method stub
	}

}
