package com.blame.artneunet.network.exception;

@SuppressWarnings("serial")
public class BuildNetworkException extends Exception {

	public BuildNetworkException(String cause) {
		super("Error building network: " + cause);
	}
}
