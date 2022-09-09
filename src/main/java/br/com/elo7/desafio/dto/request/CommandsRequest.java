package br.com.elo7.desafio.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommandsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	String movements = "";

	@NotNull
	@Min(value = 0, message = "Invalid mission id")
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
