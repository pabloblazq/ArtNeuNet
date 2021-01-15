package com.blame.artneunet.network.neuron;

import java.util.ArrayList;
import java.util.List;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.connection.Connection;

@SuppressWarnings("serial")
public class InputNeuron extends Neuron {
	
	protected List<Connection> outConnections;

	public InputNeuron(Network network) {
		super(network);
	}
	
	public void addOutConnection(Connection connection) {
		if(outConnections == null) {
			outConnections = new ArrayList<>();
		}
		outConnections.add(connection);
	}
	
}
