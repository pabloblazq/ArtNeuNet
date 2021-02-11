package com.blame.artneunet.network.connection;

import java.io.Serializable;

import com.blame.artneunet.network.neuron.Neuron;

@SuppressWarnings("serial")
public class Connection implements Serializable {

	protected Double weight;
	
	protected Neuron inputNeuron;
	protected Neuron outputNeuron;
	
	/**
	 * 
	 * @param inputNeuron
	 * @param outputNeuron
	 */
	public Connection(Neuron inputNeuron, Neuron outputNeuron) {
		this.inputNeuron = inputNeuron;
		this.outputNeuron = outputNeuron;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWeight() {
		return weight;
	}

	public Neuron getInputNeuron() {
		return inputNeuron;
	}
	
	
}
