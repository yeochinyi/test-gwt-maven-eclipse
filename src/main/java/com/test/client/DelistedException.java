package com.test.client;

import java.io.Serializable;

public class DelistedException extends Exception implements Serializable {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 5581873234660696460L;
	private String symbol;

	  public DelistedException() {
	  }

	  public DelistedException(String symbol) {
	    this.symbol = symbol;
	  }

	  public String getSymbol() {
	    return this.symbol;
	  }

}
