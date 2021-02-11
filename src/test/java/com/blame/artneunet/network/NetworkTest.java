package com.blame.artneunet.network;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class NetworkTest {

	@Test
	public void testNetwork() {
		
		Network network = new Network(2, Arrays.asList(4), 2);
		network.randomizeConnections();
		network.setInputLayerValues(Arrays.asList(0.2d, 0.8d));
		network.processNetwork();
		List<Double> outputLayerValues = network.getOutputLayerValues();
		System.out.println(outputLayerValues);
	}
}
