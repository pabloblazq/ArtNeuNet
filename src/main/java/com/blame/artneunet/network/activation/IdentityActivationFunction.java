package com.blame.artneunet.network.activation;

public class IdentityActivationFunction implements IActivationFunction {

	@Override
	public float calculateActivationValue(float processedValue, int numOfInConnections) {
		return processedValue;
	}

}
