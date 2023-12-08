package br.com.elo7.desafio.enums;

import br.com.elo7.desafio.exceptions.BusinessException;

public enum DirectionEnums {

	NORTH {
		@Override
		public DirectionEnums turnLeft() {
			return WEST;
		}

		@Override
		public DirectionEnums turnRight() {
			return EAST;
		}
	},
	WEST {
		@Override
		public DirectionEnums turnLeft() {
			return SOUTH;
		}

		@Override
		public DirectionEnums turnRight() {
			return NORTH;
		}
	},
	SOUTH {
		@Override
		public DirectionEnums turnLeft() {
			return EAST;
		}

		@Override
		public DirectionEnums turnRight() {
			return WEST;
		}
	},
	EAST {
		@Override
		public DirectionEnums turnLeft() {
			return NORTH;
		}

		@Override
		public DirectionEnums turnRight() {
			return SOUTH;
		}
	};

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

	public abstract DirectionEnums turnLeft();

	public abstract DirectionEnums turnRight();

}
