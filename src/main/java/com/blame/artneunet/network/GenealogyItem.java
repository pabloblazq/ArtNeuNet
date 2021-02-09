package com.blame.artneunet.network;

public enum GenealogyItem {

	EVA("E"),
	MUTANT("M");

	protected final String code;
	
	private GenealogyItem(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public String toString() {
		return code;
	}
}
