package br.com.elo7.desafio.exceptions;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> entityNotFound(NoSuchElementException e, HttpServletRequest request) {
		StandardError standardError = new StandardError(HttpStatus.NOT_FOUND, "Resource not found",
				request.getRequestURI(), e.getMessage());
		return ResponseEntity.status(standardError.getStatus()).body(standardError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST, null, request.getRequestURI(),
				e.getMessage());

		e.getBindingResult().getFieldErrors().forEach(error -> {
			standardError.setError(error.getDefaultMessage());
		});

		return ResponseEntity.status(standardError.getStatus()).body(standardError);
	}

	@ExceptionHandler(OccupiedLocationException.class)
	public ResponseEntity<StandardError> occupiedLocationException(OccupiedLocationException e,
			HttpServletRequest request) {
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST, "Invalid request",
				request.getRequestURI(), e.getMessage());
		return ResponseEntity.status(standardError.getStatus()).body(standardError);
	}

	@ExceptionHandler(OutRangeException.class)
	public ResponseEntity<StandardError> outRangeException(OutRangeException e, HttpServletRequest request) {
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST, "Invalid request",
				request.getRequestURI(), e.getMessage());
		return ResponseEntity.status(standardError.getStatus()).body(standardError);
	}
	
	@ExceptionHandler(InvalidCommandException.class)
	public ResponseEntity<StandardError> invalidCommandException(InvalidCommandException e, HttpServletRequest request) {
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST, "Invalid request",
				request.getRequestURI(), e.getMessage());
		return ResponseEntity.status(standardError.getStatus()).body(standardError);
	}
	
	@ExceptionHandler(ShipInMissionException.class)
	public ResponseEntity<StandardError> invalidCommandException(ShipInMissionException e, HttpServletRequest request) {
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST, "Invalid request",
				request.getRequestURI(), e.getMessage());
		return ResponseEntity.status(standardError.getStatus()).body(standardError);
	}
}
