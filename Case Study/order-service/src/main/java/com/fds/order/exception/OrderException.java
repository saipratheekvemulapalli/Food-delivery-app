package com.fds.order.exception;


public class OrderException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public OrderException(String s) {
		super(s);
	}
	
	public OrderException() {
		super();
	}

}
