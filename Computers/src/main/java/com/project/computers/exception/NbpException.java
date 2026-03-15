package com.project.computers.exception;

public class NbpException extends RuntimeException {

	/**
	 * The class for NBP exception.
	 */
	private static final long serialVersionUID = 1L;

	public NbpException(String message) {
		super(message);
	}

	public NbpException(String message, Throwable cause) {
		super(message, cause);
	}
}