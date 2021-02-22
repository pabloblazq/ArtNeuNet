package com.blame.artneunet.network.activation;

@SuppressWarnings("serial")
public class BinaryStepActivationFunction implements IActivationFunction {

	protected Double triggerThreshold;
	protected Double activationValue;
	
	public BinaryStepActivationFunction(double triggerThreshold, double activationValue) {
		super();
		this.triggerThreshold = triggerThreshold;
		this.activationValue = activationValue;
	}

	@Override
	public double calculateActivationValue(double processedValue, int numOfInConnections) {
		if(processedValue > triggerThreshold) {
			return activationValue;
		} else {
			return 0d;
		}
	}

}
