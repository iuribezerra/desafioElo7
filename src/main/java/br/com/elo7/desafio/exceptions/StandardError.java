package br.com.elo7.desafio.exceptions;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.http.HttpStatus;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String error;
	private Instant timestamp;
	private String path;
	private String message;

	public StandardError() {
		
	}
	
	public StandardError(HttpStatus status, String error, String path, String message) {
		this.status = status;
		this.error = error;
		this.timestamp = Instant.now();
		this.path = path;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
