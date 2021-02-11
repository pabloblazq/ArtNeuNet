package com.blame.artneunet.network.neuron;

import java.util.ArrayList;
import java.util.List;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.connection.Connection;

@SuppressWarnings("serial")
public class ProcessingNeuron extends Neuron {

	protected List<Connection> inConnections;
	protected List<Connection> outConnections;

	/**
	 * 
	 * @param network
	 */
	public ProcessingNeuron(Network network) {
		super(network);
	}
	
	/**
	 * 
	 * @param connection
	 */
	public void addInConnection(Connection connection) {
		if(inConnections == null) {
			inConnections = new ArrayList<>();
		}
		inConnections.add(connection);
	}

	/**
	 * 
	 * @param connection
	 */
	public void addOutConnection(Connection connection) {
		if(outConnections == null) {
			outConnections = new ArrayList<>();
		}
		outConnections.add(connection);
	}

	/**
	 * 
	 */
	public void process() {
		
		double processingValue = 0d;
		for(Connection inConnection : inConnections) {
			processingValue += inConnection.getInputNeuron().getValue() * inConnection.getWeight();
		}
		
		value = network.getProcessingActivationFunction().calculateActivationValue(processingValue, inConnections.size());
	}

}
