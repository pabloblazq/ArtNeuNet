package com.blame.artneunet.network.activation;

public class BinaryStepActivationFunction implements IActivationFunction {

	protected Float triggerThreshold;
	protected Float activationValue;
	
	public BinaryStepActivationFunction(float triggerThreshold, float activationValue) {
		super();
		this.triggerThreshold = triggerThreshold;
		this.activationValue = activationValue;
	}

	@Override
	public float calculateActivationValue(float processedValue, int numOfInConnections) {
		if(processedValue > triggerThreshold) {
			return activationValue;
		} else {
			return 0f;
		}
	}

}
