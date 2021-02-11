package com.blame.artneunet.network.activation;

public class SigmoidActivationFunction implements IActivationFunction {

	@Override
	public double calculateActivationValue(double processedValue, int numOfInConnections) {
		
		return 1d / (1d + Math.exp(-processedValue));
	}

}
