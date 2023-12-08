package br.com.elo7.desafio.enums;

import br.com.elo7.desafio.exceptions.BusinessException;

public enum TurnEnums {

	LEFT("L") {
		@Override
		public DirectionEnums newDirection(DirectionEnums directionEnums) {
			return directionEnums.turnLeft();
		}
	}, RIGHT("R") {
		@Override
		public DirectionEnums newDirection(DirectionEnums directionEnums) {
			return directionEnums.turnRight();
		}
	};

	private String firstLetter;

	private TurnEnums(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public static TurnEnums value(String firstLetter) {
		for (TurnEnums value : TurnEnums.values()) {
			if (value.getFirstLetter().equalsIgnoreCase(firstLetter)) {
				return value;
			}
		}

		throw new BusinessException("Invalid value for Turn");
	}

	public abstract DirectionEnums newDirection(DirectionEnums directionEnums);

}
