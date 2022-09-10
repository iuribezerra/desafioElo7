package br.com.elo7.desafio.enums;

import br.com.elo7.desafio.exceptions.BusinessException;

public enum TurnEnums {

	LEFT(1), RIGHT(2);

	private int code;

	private TurnEnums(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static TurnEnums valueOf(int code) {
		for (TurnEnums value : TurnEnums.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}

		throw new BusinessException("Invalid value for Turn");
	}

}
