package com.blame.artneunet.network;

import java.util.ArrayList;
import java.util.List;

import com.blame.artneunet.network.activation.IActivationFunction;

public class NetworkBuilder {

	protected int numberOfInputNeurons;
	protected List<Integer> numberOfProcessingNeurons;
	protected int numberOfOutputNeurons;

	protected Class<IActivationFunction> processingActivationFunctionClass;
	protected Class<IActivationFunction> outputActivationFunctionClass;
	
	public NetworkBuilder() {
		super();
	}
	
	public NetworkBuilder setNumberOfInputNeurons(int numberOfInputNeurons) {
		this.numberOfInputNeurons = numberOfInputNeurons;
		return this;
	}

	public NetworkBuilder setNumberOfProcessingNeurons(List<Integer> numberOfProcessingNeurons) {
		this.numberOfProcessingNeurons = numberOfProcessingNeurons;
		return this;
	}

	public NetworkBuilder setNumberOfOutputNeurons(int numberOfOutputNeurons) {
		this.numberOfOutputNeurons = numberOfOutputNeurons;
		return this;
	}

	public NetworkBuilder setProcessingActivationFunctionClass(Class<IActivationFunction> processingActivationFunctionClass) {
		this.processingActivationFunctionClass = processingActivationFunctionClass;
		return this;
	}

	public NetworkBuilder setOutputActivationFunctionClass(Class<IActivationFunction> outputActivationFunctionClass) {
		this.outputActivationFunctionClass = outputActivationFunctionClass;
		return this;
	}

	/**
	 * 
	 * @param numberOfNetworks
	 * @return
	 */
	public List<Network> buildNetworks(int numberOfNetworks, boolean randomizeConnections) {
		
		List<Network> networks = new ArrayList<>();
		for(int i = 0; i < numberOfNetworks; i++) {
			networks.add(buildNetwork(randomizeConnections));
		}
		
		return networks;
	}

	/**
	 * 
	 * @return
	 */
	public Network buildNetwork(boolean randomizeConnections) {

		Network network = new Network(numberOfInputNeurons, numberOfProcessingNeurons, numberOfOutputNeurons);
		try {
			network.setProcessingActivationFunction(processingActivationFunctionClass.newInstance());
			network.setOutputActivationFunction(outputActivationFunctionClass.newInstance());
		} catch (Exception e) {/*nothing to do*/}
		
		if(randomizeConnections) {
			network.randomizeConnections();
		}
		
		return network;
	}
}
