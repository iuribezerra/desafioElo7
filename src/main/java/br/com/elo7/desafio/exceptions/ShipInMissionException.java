package br.com.elo7.desafio.exceptions;

public class ShipInMissionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ShipInMissionException() {
		super("ship is already on mission");
	}
}
