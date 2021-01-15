package com.blame.artneunet.problemarena;

import java.util.Arrays;

import org.junit.Test;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.activation.SigmoidActivationFunction;
import com.blame.artneunet.network.activation.TanhActivationFunction;
import com.blame.artneunet.network.exception.BuildNetworkException;

public class ChaserProblemArenaTest {

	@Test
	public void testChaserProblemArena() throws BuildNetworkException {
		
		Network network = new Network(4, 2, Arrays.asList(2));
		network.setProcessingActivationFunction(new SigmoidActivationFunction());
		network.setOutputActivationFunction(new TanhActivationFunction());
		network.randomizeConnections();

		ChaserProblemArena chaserProblemArena = new ChaserProblemArena(network);
		chaserProblemArena.processProblem();
		float resultValue = chaserProblemArena.calculateResultValue();
		System.out.println(resultValue);
	}
}
