package com.blame.artneunet.network.neuron;

import java.util.ArrayList;
import java.util.List;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.connection.Connection;

@SuppressWarnings("serial")
public class OutputNeuron extends Neuron {

	protected List<Connection> inConnections;

	public OutputNeuron(Network network) {
		super(network);
	}
	
	public void addInConnection(Connection connection) {
		if(inConnections == null) {
			inConnections = new ArrayList<>();
		}
		inConnections.add(connection);
	}

	/**
	 * 
	 */
	public void process() {

		float processingValue = 0f;
		for(Connection inConnection : inConnections) {
			processingValue += inConnection.getInputNeuron().getValue() * inConnection.getWeight();
		}

		value = network.getOutputActivationFunction().calculateActivationValue(processingValue, inConnections.size());
	}

}
