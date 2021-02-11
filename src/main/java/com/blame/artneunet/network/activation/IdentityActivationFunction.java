package com.blame.artneunet.network.activation;

public class IdentityActivationFunction implements IActivationFunction {

	@Override
	public double calculateActivationValue(double processedValue, int numOfInConnections) {
		return processedValue;
	}

}
