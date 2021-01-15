package com.blame.artneunet.network.activation;

public class SigmoidActivationFunction implements IActivationFunction {

	@Override
	public float calculateActivationValue(float processedValue, int numOfInConnections) {
		
		double dProcessedValue = Double.parseDouble(Float.toString(processedValue));
		double sigmoid = 1d / (1d + Math.exp(-dProcessedValue));
		return (float) sigmoid;
	}

}
