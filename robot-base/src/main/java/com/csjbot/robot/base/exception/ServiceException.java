package com.csjbot.robot.base.exception;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = -6537593572489712532L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
