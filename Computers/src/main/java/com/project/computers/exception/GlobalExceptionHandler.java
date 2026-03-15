package com.project.computers.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The global exception handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private final String ExceptionMessageError = "Internal Server Error";

	private final String NbpExceptionMessageError = "NBP API Error";

	/**
	 * Handle default exception.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleException(Exception ex, HttpServletRequest request) {

		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionMessageError, ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	/**
	 * Handle NBP exception.
	 */
	@ExceptionHandler(NbpException.class)
	public ResponseEntity<ApiError> handleNbpException(NbpException ex, HttpServletRequest request) {

		ApiError error = new ApiError(HttpStatus.BAD_GATEWAY.value(), NbpExceptionMessageError, ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
	}
}