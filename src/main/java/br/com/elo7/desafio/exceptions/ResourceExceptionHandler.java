package br.com.elo7.desafio.exceptions;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<StandardError> emptyResultDataAccessException(EmptyResultDataAccessException e, HttpServletRequest request) {
		StandardError standardError = new StandardError(HttpStatus.NOT_FOUND, "Resource not found",
				request.getRequestURI(), e.getMessage());
		return ResponseEntity.status(standardError.getStatus()).body(standardError);
	}

	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST, "Constrain Violation",
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

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<StandardError> invalidCommandException(BusinessException e, HttpServletRequest request) {
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST, "Invalid request",
				request.getRequestURI(), e.getMessage());
		return ResponseEntity.status(standardError.getStatus()).body(standardError);
	}
}
