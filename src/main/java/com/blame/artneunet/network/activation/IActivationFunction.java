package com.blame.artneunet.network.activation;

public interface IActivationFunction {

	public double calculateActivationValue(double processedValue, int numOfInConnections);
}
