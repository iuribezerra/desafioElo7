package br.com.elo7.desafio.exceptions;

public class InvalidCommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCommandException() {
		super("Use only L for turn Left, R for turn Right or M to Move");
	}
}
