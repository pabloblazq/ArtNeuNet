package com.blame.artneunet.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.blame.artneunet.network.activation.IActivationFunction;
import com.blame.artneunet.network.activation.IdentityActivationFunction;
import com.blame.artneunet.network.connection.Connection;
import com.blame.artneunet.network.neuron.InputNeuron;
import com.blame.artneunet.network.neuron.OutputNeuron;
import com.blame.artneunet.network.neuron.ProcessingNeuron;

@SuppressWarnings("serial")
public class Network implements Serializable {

	protected IActivationFunction processingActivationFunction;
	protected IActivationFunction outputActivationFunction;

	protected List<InputNeuron> inputLayer;
	protected List<List<ProcessingNeuron>> processingLayerList;
	protected List<OutputNeuron> outputLayer;
	protected List<Connection> connectionList;
	
	protected Random random;

	/**
	 * 
	 * @param numOfInputNeurons
	 * @param numOfProcessingNeurons
	 * @param numOfOutputNeurons
	 */
	public Network(int numOfInputNeurons, List<Integer> numOfProcessingNeurons, int numOfOutputNeurons) {
		
		processingActivationFunction = new IdentityActivationFunction(); // as default
		outputActivationFunction = new IdentityActivationFunction(); // as default
		
		// build the input layer
		inputLayer = new ArrayList<>();
		for(int i = 0; i < numOfInputNeurons; i++) {
			inputLayer.add(new InputNeuron(this));
		}
		
		// build the output layer
		outputLayer = new ArrayList<>();
		for(int i = 0; i < numOfOutputNeurons; i++) {
			outputLayer.add(new OutputNeuron(this));
		}

		// build the processing layers
		processingLayerList = new ArrayList<>();
		for(int numOfProcessingNeuronsForLayer : numOfProcessingNeurons) {
			
			List<ProcessingNeuron> processingLayer = new ArrayList<>();
			for(int i = 0; i < numOfProcessingNeuronsForLayer; i++) {
				processingLayer.add(new ProcessingNeuron(this));
			}
			
			processingLayerList.add(processingLayer);
		}
		
		// build the connections
		connectionList = new ArrayList<>();
		
		// build the connections from the input layer
		for(InputNeuron inputNeuron : inputLayer) {
			for(ProcessingNeuron outputNeuron : processingLayerList.get(0)) {
				Connection connection = new Connection(inputNeuron, outputNeuron);
				inputNeuron.addOutConnection(connection);
				outputNeuron.addInConnection(connection);
				connectionList.add(connection);
			}
		}
		
		// build the connections between the processing layers
		for(int iLayer = 0; iLayer < processingLayerList.size() -1; iLayer++) {
			for(ProcessingNeuron inputNeuron : processingLayerList.get(iLayer)) {
				for(ProcessingNeuron outputNeuron : processingLayerList.get(iLayer+1)) {
					Connection connection = new Connection(inputNeuron, outputNeuron);
					inputNeuron.addOutConnection(connection);
					outputNeuron.addInConnection(connection);
					connectionList.add(connection);
				}
			}
		}
		
		// build the connections for the output layer
		for(ProcessingNeuron inputNeuron : processingLayerList.get(processingLayerList.size() -1)) {
			for(OutputNeuron outputNeuron : outputLayer) {
				Connection connection = new Connection(inputNeuron, outputNeuron);
				inputNeuron.addOutConnection(connection);
				outputNeuron.addInConnection(connection);
				connectionList.add(connection);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public IActivationFunction getProcessingActivationFunction() {
		return processingActivationFunction;
	}
	
	/**
	 * 
	 * @return
	 */
	public IActivationFunction getOutputActivationFunction() {
		return outputActivationFunction;
	}
	
	/**
	 * 
	 * @param activationValue
	 */
	public void setProcessingActivationFunction(IActivationFunction activationFunction) {
		this.processingActivationFunction = activationFunction;
	}
	
	/**
	 * 
	 * @param activationValue
	 */
	public void setOutputActivationFunction(IActivationFunction activationFunction) {
		this.outputActivationFunction = activationFunction;
	}
	
	/**
	 * 
	 */
	public void randomizeConnections() {
		if(random == null) {
			random = new Random();
		}
		
		for(Connection connection : connectionList) {
			connection.setWeight(generateRandomWeight());
		}
	}

	/**
	 * 
	 * @return
	 */
	protected float generateRandomWeight() {
		float weight = random.nextFloat(); // generates unsigned float
		if(random.nextBoolean()) {
			weight = - weight;
		}
		return weight;
	}
	
	/**
	 * 
	 * @param inValues
	 */
	public void setInputLayerValues(List<Float> inValues) {
		for(int i = 0; i < inputLayer.size(); i++) {
			InputNeuron inputNeuron = inputLayer.get(i);
			inputNeuron.setValue(inValues.get(i));
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Float> getOutputLayerValues() {
		return outputLayer.stream().map(outputNeuron -> outputNeuron.getValue()).collect(Collectors.toList());
	}
	
	/**
	 * 
	 */
	public void processNetwork() {
		// processing layers
		for(List<ProcessingNeuron> processingLayer : processingLayerList) {
			for(ProcessingNeuron processingNeuron : processingLayer) {
				processingNeuron.process();
			}
		}
		
		// output layer
		for(OutputNeuron outputNeuron : outputLayer) {
			outputNeuron.process();
		}
	}

	/**
	 * 
	 * @return
	 * @throws BuildNetworkException
	 */
	public Network cloneNetwork() {
		
		List<Integer> numOfProcessingNeurons = processingLayerList.stream().map(processingLayer -> processingLayer.size()).collect(Collectors.toList());
		Network cloneNetwork = new Network(inputLayer.size(), numOfProcessingNeurons, outputLayer.size());
		for(int i = 0; i < this.connectionList.size(); i++) {
			Float weight = this.connectionList.get(i).getWeight();
			if(weight != null) {
				cloneNetwork.connectionList.get(i).setWeight(new Float(weight));
			}
		}
		
		return cloneNetwork;
	}
	
}
