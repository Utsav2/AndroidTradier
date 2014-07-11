package com.steins.tradier;

public abstract class Stock {

	private String mSymbol;

	public Stock(String symbol) {

		this.setSymbol(symbol);
	}

	public String getSymbol() {
		return mSymbol;
	}

	private void setSymbol(String mSymbol) {
		this.mSymbol = mSymbol;
	}

}
