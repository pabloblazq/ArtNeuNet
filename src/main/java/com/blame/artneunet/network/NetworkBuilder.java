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
	
	public static NetworkBuilder createNetworkBuilder() {
		return new NetworkBuilder();
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

	public int getNumberOfInputNeurons() {
		return numberOfInputNeurons;
	}

	public List<Integer> getNumberOfProcessingNeurons() {
		return numberOfProcessingNeurons;
	}

	public int getNumberOfOutputNeurons() {
		return numberOfOutputNeurons;
	}

	public NetworkBuilder setProcessingActivationFunctionClass(Class<? extends IActivationFunction> processingActivationFunctionClass) {
		this.processingActivationFunctionClass = (Class<IActivationFunction>) processingActivationFunctionClass;
		return this;
	}

	public NetworkBuilder setOutputActivationFunctionClass(Class<? extends IActivationFunction> outputActivationFunctionClass) {
		this.outputActivationFunctionClass = (Class<IActivationFunction>) outputActivationFunctionClass;
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
			network.setProcessingActivationFunction(processingActivationFunctionClass.getConstructor().newInstance());
			network.setOutputActivationFunction(outputActivationFunctionClass.getConstructor().newInstance());
		} catch (Exception e) {/*nothing to do*/}
		
		if(randomizeConnections) {
			network.randomizeConnections();
		}
		
		return network;
	}

	
	/**
	 * 
	 * @param network
	 * @return
	 */
	public Network generateMutatedNetwork(Network network) {
		
		Network copyNetwork = network.cloneNetwork();
		copyNetwork.setEnabled(true);
		copyNetwork.randomizeConnection();
		return copyNetwork;
	}
}
