package com.fong2.serviceRtException;

public class AlreadyExistingException extends RuntimeException {

	private static final long serialVersionUID = 2807979717029970067L;

	public AlreadyExistingException() {
		// TODO Auto-generated constructor stub
	}

	public AlreadyExistingException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AlreadyExistingException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public AlreadyExistingException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AlreadyExistingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
