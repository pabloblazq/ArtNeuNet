package com.blame.artneunet.network.activation;

@SuppressWarnings("serial")
public class IdentityActivationFunction implements IActivationFunction {

	@Override
	public double calculateActivationValue(double processedValue, int numOfInConnections) {
		return processedValue;
	}

}
