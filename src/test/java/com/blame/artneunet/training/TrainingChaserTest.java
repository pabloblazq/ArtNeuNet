package com.blame.artneunet.training;

import java.util.Arrays;

import org.junit.Test;

import com.blame.artneunet.network.NetworkBuilder;
import com.blame.artneunet.network.activation.TanhActivationFunction;
import com.blame.artneunet.problemarena.chaser.ChaserProblemArena;

public class TrainingChaserTest {

	@Test
	public void testTraining() throws InterruptedException {
		
		NetworkBuilder networkBuilder = NetworkBuilder.createNetworkBuilder()
			.setNumberOfInputNeurons(4)
			.setNumberOfProcessingNeurons(Arrays.asList(4))
			.setNumberOfOutputNeurons(2)
			.setProcessingActivationFunctionClass(TanhActivationFunction.class)
			.setOutputActivationFunctionClass(TanhActivationFunction.class);

		Training training = new Training(networkBuilder, ChaserProblemArena.class);
		training.processTraining();
	}

}