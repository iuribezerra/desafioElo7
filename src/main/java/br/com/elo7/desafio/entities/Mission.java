package br.com.elo7.desafio.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.elo7.desafio.enums.DirectionEnums;
import br.com.elo7.desafio.enums.TurnEnums;

@Entity
@Table(name = "TB_MISSION")
public class Mission implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private final int MOVEMENTS = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Planet planet;

	private Ship ship;

	private Integer shipPositionX;

	private Integer shipPositionY;

	private DirectionEnums shipPointing;

	public Mission() {

	}

	public Mission(Planet planet, Ship ship, Integer shipPositionX, Integer shipPositionY,
			DirectionEnums shipPointing) {
		this.planet = planet;
		this.ship = ship;
		this.shipPositionX = shipPositionX;
		this.shipPositionY = shipPositionY;
		this.shipPointing = shipPointing;
	}

	public Long getId() {
		return id;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
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
		return shipPointing;
	}

	public void setShipPointing(DirectionEnums shipPointing) {
		this.shipPointing = shipPointing;
	}

	public void moveShip() {
		switch (getShipPointing()) {
		case NORTH:
			this.setShipPositionY(this.getShipPositionY() + MOVEMENTS);
			break;
		case SOUTH:
			this.setShipPositionY(this.getShipPositionY() - MOVEMENTS);
			break;
		case EAST:
			this.setShipPositionX(this.getShipPositionX() + MOVEMENTS);
			break;
		case WEST:
			this.setShipPositionX(this.getShipPositionX() - MOVEMENTS);
			break;
		}
	}

	/*
	 * Move left - Increase array / Move rigth - Decrease array
	 */
	public void turn(TurnEnums turn) {
		final int initialPosition = 1;
		final int finalPosition = DirectionEnums.values().length;
		if (turn.equals(TurnEnums.LEFT)) {
			int newCode = this.getShipPointing().getCode() + 1;

			if (newCode > finalPosition) {
				this.setShipPointing(DirectionEnums.valueOf(initialPosition));
			} else {
				this.setShipPointing(DirectionEnums.valueOf(newCode));
			}
		} else {
			int newCode = this.getShipPointing().getCode() - 1;

			if (newCode < initialPosition) {
				this.setShipPointing(DirectionEnums.valueOf(finalPosition));
			} else {
				this.setShipPointing(DirectionEnums.valueOf(newCode));
			}
		}
	}

}
