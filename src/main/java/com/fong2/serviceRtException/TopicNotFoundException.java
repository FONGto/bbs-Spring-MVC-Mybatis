package com.fong2.serviceRtException;

public class TopicNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1021328602695181446L;

	public TopicNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public TopicNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TopicNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TopicNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TopicNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
