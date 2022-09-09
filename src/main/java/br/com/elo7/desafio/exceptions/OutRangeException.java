package br.com.elo7.desafio.exceptions;

public class OutRangeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OutRangeException(String message) {
		super(message);
	}
}
