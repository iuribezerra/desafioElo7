package br.com.elo7.desafio.dto.request;

import br.com.elo7.desafio.enums.DirectionEnums;

public class MissionRequest {

	private Long id;

	private Long planetId;

	private Long shipId;

	private Integer shipPositionX;

	private Integer shipPositionY;

	private String shipPointing;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
