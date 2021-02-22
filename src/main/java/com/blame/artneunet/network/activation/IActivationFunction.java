package com.blame.artneunet.network.activation;

import java.io.Serializable;

public interface IActivationFunction extends Serializable {

	public double calculateActivationValue(double processedValue, int numOfInConnections);
}
