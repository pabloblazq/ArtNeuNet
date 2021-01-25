package com.blame.artneunet.problemarena;

import java.util.Arrays;

import org.junit.Test;

import com.blame.artneunet.graphics.ChaserProblemDisplay;
import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.activation.SigmoidActivationFunction;
import com.blame.artneunet.network.activation.TanhActivationFunction;

public class ChaserProblemDisplayTest {

	private static final int DIMENSION_X = 1000;
	private static final int DIMENSION_Y = 1000;
	private static final int NUM_PROBLEM_ITERATIONS = 200;

	@Test
	public void testChaserProblemDisplay() throws InterruptedException {
		
		Network network = new Network(4, Arrays.asList(2), 2);
		network.setProcessingActivationFunction(new TanhActivationFunction());
		network.setOutputActivationFunction(new TanhActivationFunction());
		network.randomizeConnections();

		ChaserProblemArena chaserProblemArena = new ChaserProblemArena(network, (float) DIMENSION_X, (float) DIMENSION_Y, NUM_PROBLEM_ITERATIONS);
		chaserProblemArena.processProblem();
		float resultValue = chaserProblemArena.calculateResultValue();
		System.out.println(resultValue);
		
		ChaserProblemDisplay chaserProblemDisplay = new ChaserProblemDisplay(DIMENSION_X, DIMENSION_Y);
		chaserProblemDisplay.setProblemArena(chaserProblemArena);
		while(!chaserProblemDisplay.incrementIteration()) {
			Thread.sleep(100);
			chaserProblemDisplay.repaint();
		}
		Thread.sleep(5000);
	}
}
