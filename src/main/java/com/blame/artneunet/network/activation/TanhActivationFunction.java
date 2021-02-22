package com.blame.artneunet.network.activation;

@SuppressWarnings("serial")
public class TanhActivationFunction implements IActivationFunction {

	@Override
	public double calculateActivationValue(double processedValue, int numOfInConnections) {
		
		return Math.tanh(processedValue);
	}

}
