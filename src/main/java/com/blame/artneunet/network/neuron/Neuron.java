package com.blame.artneunet.network.neuron;

import java.io.Serializable;

import com.blame.artneunet.network.Network;

@SuppressWarnings("serial")
public abstract class Neuron implements Serializable {

	protected Float value;
	protected Network network;
	
	/**
	 * 
	 * @return
	 */
	public Float getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(Float value) {
		this.value = value;
	}

	/**
	 * 
	 * @param network
	 */
	public Neuron(Network network) {
		this.network = network;
	}
	
}
