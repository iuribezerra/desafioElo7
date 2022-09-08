package br.com.elo7.desafio.dto.request;

public class Commands {

	String movements = "";
	Long missionId;

	public Long getMissionId() {
		return missionId;
	}

	/*
	 * expected L-left, R-right, M-move
	 */
	public String[] getMovements() {
		String movements = this.movements.replaceAll("[^Ll Rr Mm ]", "");
		if (movements.length() > 0) {
			return movements.split("");
		}
		return new String[0];
	}

}
