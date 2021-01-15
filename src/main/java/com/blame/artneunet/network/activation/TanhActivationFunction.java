package com.blame.artneunet.network.activation;

public class TanhActivationFunction implements IActivationFunction {

	@Override
	public float calculateActivationValue(float processedValue, int numOfInConnections) {
		
		double dProcessedValue = Double.parseDouble(Float.toString(processedValue));
		double tanh = Math.tanh(dProcessedValue);
		return (float) tanh;
	}

}
