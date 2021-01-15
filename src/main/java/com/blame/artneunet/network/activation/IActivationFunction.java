package com.blame.artneunet.network.activation;

public interface IActivationFunction {

	public float calculateActivationValue(float processedValue, int numOfInConnections);
}
