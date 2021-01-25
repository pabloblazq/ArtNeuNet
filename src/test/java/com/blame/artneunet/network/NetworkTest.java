package com.blame.artneunet.network;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.blame.artneunet.network.Network;

public class NetworkTest {

	@Test
	public void testNetwork() {
		
		Network network = new Network(2, Arrays.asList(4), 2);
		network.randomizeConnections();
		network.setInputLayerValues(Arrays.asList(0.2f, 0.8f));
		network.processNetwork();
		List<Float> outputLayerValues = network.getOutputLayerValues();
		System.out.println(outputLayerValues);
	}
}
