package com.blame.artneunet.training;

import java.util.Arrays;

import org.junit.Test;

import com.blame.artneunet.network.NetworkBuilder;
import com.blame.artneunet.network.activation.TanhActivationFunction;
import com.blame.artneunet.problemarena.racing.RacingProblemArena;

public class TrainingRacingTest {

	@Test
	public void testTraining() throws InterruptedException {
		
		NetworkBuilder networkBuilder = NetworkBuilder.createNetworkBuilder()
			.setNumberOfInputNeurons(7)
			.setNumberOfProcessingNeurons(Arrays.asList(8, 4))
			.setNumberOfOutputNeurons(2)
			.setProcessingActivationFunctionClass(TanhActivationFunction.class)
			.setOutputActivationFunctionClass(TanhActivationFunction.class);

		Training training = new Training(networkBuilder, RacingProblemArena.class);
		training.processTraining();
	}

}
