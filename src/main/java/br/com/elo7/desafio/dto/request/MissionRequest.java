package br.com.elo7.desafio.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.elo7.desafio.enums.DirectionEnums;

public class MissionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Min(value = 0, message = "Invalid planet id")
	private Long planetId;

	@NotNull
	@Min(value = 0, message = "Invalid ship id")
	private Long shipId;

	@NotNull
	private Integer shipPositionX;

	@NotNull
	private Integer shipPositionY;

	@NotBlank
	private String shipPointing;

	public MissionRequest(Long planetId, Long shipId, Integer shipPositionX, Integer shipPositionY,
			String shipPointing) {
		this.planetId = planetId;
		this.shipId = shipId;
		this.shipPositionX = shipPositionX;
		this.shipPositionY = shipPositionY;
		this.shipPointing = shipPointing;
	}

	public Long getPlanetId() {
		return planetId;
	}

	public void setPlanetId(Long planetId) {
		this.planetId = planetId;
	}

	public Long getShipId() {
		return shipId;
	}

	public void setShipId(Long shipId) {
		this.shipId = shipId;
	}

	public Integer getShipPositionX() {
		return shipPositionX;
	}

	public void setShipPositionX(Integer shipPositionX) {
		this.shipPositionX = shipPositionX;
	}

	public Integer getShipPositionY() {
		return shipPositionY;
	}

	public void setShipPositionY(Integer shipPositionY) {
		this.shipPositionY = shipPositionY;
	}

	public DirectionEnums getShipPointing() {
		return DirectionEnums.valueByName(this.shipPointing);
	}

	public void setShipPointing(String shipPointing) {
		this.shipPointing = shipPointing;
	}

}
