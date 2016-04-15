package com.pkuerzer.exception;

public class NumberOfSpielerException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NumberOfSpielerException(){
		super();
	}
	
	public NumberOfSpielerException(String message){
		super(message);
	}
	
	public NumberOfSpielerException(String message, Throwable cause){
		super(message, cause);
	}
	
	public NumberOfSpielerException(Throwable cause){
		super(cause);
	}

}
