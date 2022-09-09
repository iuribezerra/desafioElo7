package br.com.elo7.desafio.exceptions;

public class OccupiedLocationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OccupiedLocationException() {
		super("Movement deny, blocked way");
	}
}
