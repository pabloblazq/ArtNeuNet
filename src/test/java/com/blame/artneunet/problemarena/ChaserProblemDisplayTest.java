package com.blame.artneunet.problemarena;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.blame.artneunet.graphics.ChaserProblemDisplay;
import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.NetworkBuilder;
import com.blame.artneunet.network.activation.TanhActivationFunction;

public class ChaserProblemDisplayTest {

	@Test
	public void testChaserProblemDisplay() throws InterruptedException {
		
		List<Network> networkList = NetworkBuilder.createNetworkBuilder()
			.setNumberOfInputNeurons(4)
			.setNumberOfProcessingNeurons(Arrays.asList(2))
			.setNumberOfOutputNeurons(2)
			.setProcessingActivationFunctionClass(TanhActivationFunction.class)
			.setOutputActivationFunctionClass(TanhActivationFunction.class)
			.buildNetworks(20, true);

		ChaserProblemArena chaserProblemArena = new ChaserProblemArena(networkList);
		chaserProblemArena.processProblem();

		ChaserProblemDisplay chaserProblemDisplay = new ChaserProblemDisplay(chaserProblemArena);
		while(!chaserProblemDisplay.incrementIteration()) {
			Thread.sleep(100);
			chaserProblemDisplay.repaint();
		}
		Thread.sleep(5000);
	}

}
