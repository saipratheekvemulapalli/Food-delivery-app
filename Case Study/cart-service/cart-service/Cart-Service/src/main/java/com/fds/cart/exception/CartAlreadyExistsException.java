package com.fds.cart.exception;

public class CartAlreadyExistsException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	
	public  CartAlreadyExistsException(String message) {
		
		super(message);
	}
	
	public CartAlreadyExistsException()
	{
		
    }
}
