package br.com.elo7.desafio.enums;

import br.com.elo7.desafio.exceptions.BusinessException;

public enum DirectionEnums {

	/**
	 * Enums are sorted in a circle, based on the wind rose diagram
	 */
	NORTH(1), WEST(2), SOUTH(3), EAST(4);

	private int code;

	private DirectionEnums(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static DirectionEnums valueOf(int code) {
		for (DirectionEnums value : DirectionEnums.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}

		throw new IllegalArgumentException("Invalid code for Direction");
	}

	public static DirectionEnums valueByName(String name) {
		switch (name.toUpperCase()) {
		case "NORTH":
			return NORTH;
		case "WEST":
			return WEST;
		case "SOUTH":
			return SOUTH;
		case "EAST":
			return EAST;
		default:
			throw new BusinessException("Invalid value for Direction");
		}

	}

}
