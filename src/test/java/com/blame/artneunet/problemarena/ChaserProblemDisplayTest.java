package com.blame.artneunet.problemarena;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.blame.artneunet.graphics.chaser.ChaserProblemDisplay;
import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.NetworkBuilder;
import com.blame.artneunet.network.activation.TanhActivationFunction;
import com.blame.artneunet.problemarena.chaser.ChaserProblemArena;

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

		ChaserProblemDisplay chaserProblemDisplay = ChaserProblemDisplay.getChaserProblemDisplay();
		chaserProblemDisplay.initializeChaserProblemArena(chaserProblemArena, null);
		chaserProblemDisplay.display();
	}

}
