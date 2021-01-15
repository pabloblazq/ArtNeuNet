package com.blame.artneunet.network;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.blame.artneunet.network.Network;
import com.blame.artneunet.network.exception.BuildNetworkException;

public class NetworkTest {

	@Test
	public void testNetwork() throws BuildNetworkException {
		
		Network network = new Network(2, 2, Arrays.asList(4));
		network.randomizeConnections();
		network.setInputLayerValues(Arrays.asList(0.2f, 0.8f));
		network.processNetwork();
		List<Float> outputLayerValues = network.getOutputLayerValues();
		System.out.println(outputLayerValues);
	}
}
