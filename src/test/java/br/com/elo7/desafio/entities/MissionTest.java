package br.com.elo7.desafio.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.elo7.desafio.enums.DirectionEnums;
import br.com.elo7.desafio.enums.TurnEnums;

class MissionTest {

	@Test
	@DisplayName("Move ship")
	void moveShipTest() {
		// Pointing NORTH X, Y + 1
		Ship ship = new Ship(1L, "Voyager 1");
		Planet planet = new Planet(2L, "Marte", 5, 5);
		Mission mission = new Mission(3L, planet, ship, 0, 0, DirectionEnums.NORTH);
		mission.moveShip();
		assertEquals(mission.getShipPositionX(), 0);
		assertEquals(mission.getShipPositionY(), 1);

		// Pointing SOUTH X, Y - 1
		mission = new Mission(3L, planet, ship, 0, 0, DirectionEnums.SOUTH);
		mission.moveShip();
		assertEquals(mission.getShipPositionX(), 0);
		assertEquals(mission.getShipPositionY(), -1);

		// Pointing NORTH X - 1, y
		mission = new Mission(3L, planet, ship, 0, 0, DirectionEnums.WEST);
		mission.moveShip();
		assertEquals(mission.getShipPositionX(), -1);
		assertEquals(mission.getShipPositionY(), 0);

		// Pointing NORTH X + 1, y
		mission = new Mission(3L, planet, ship, 0, 0, DirectionEnums.EAST);
		mission.moveShip();
		assertEquals(mission.getShipPositionX(), 1);
		assertEquals(mission.getShipPositionY(), 0);
	}

	@Test
	@DisplayName("Turn ship to left")
	void turnShipLeftTest() {
		// Pointing NORTH
		Ship ship = new Ship(1L, "Voyager 1");
		Planet planet = new Planet(2L, "Marte", 5, 5);
		Mission mission = new Mission(3L, planet, ship, 0, 0, DirectionEnums.NORTH);
		mission.turn(TurnEnums.LEFT);
		assertEquals(mission.getShipPointing(), DirectionEnums.WEST);

		// Pointing WEST
		new Mission(3L, planet, ship, 0, 0, DirectionEnums.WEST);
		mission.turn(TurnEnums.LEFT);
		assertEquals(mission.getShipPointing(), DirectionEnums.SOUTH);

		// Pointing SOUTH
		new Mission(3L, planet, ship, 0, 0, DirectionEnums.SOUTH);
		mission.turn(TurnEnums.LEFT);
		assertEquals(mission.getShipPointing(), DirectionEnums.EAST);

		// Pointing EAST
		new Mission(3L, planet, ship, 0, 0, DirectionEnums.EAST);
		mission.turn(TurnEnums.LEFT);
		assertEquals(mission.getShipPointing(), DirectionEnums.NORTH);
	}
	
	@Test
	@DisplayName("Turn ship to right")
	void turnShipRightTest() {
		// Pointing NORTH
		Ship ship = new Ship(1L, "Voyager 1");
		Planet planet = new Planet(2L, "Marte", 5, 5);
		Mission mission = new Mission(3L, planet, ship, 0, 0, DirectionEnums.NORTH);
		mission.turn(TurnEnums.RIGHT);
		assertEquals(mission.getShipPointing(), DirectionEnums.EAST);

		// Pointing EAST
		new Mission(3L, planet, ship, 0, 0, DirectionEnums.EAST);
		mission.turn(TurnEnums.RIGHT);
		assertEquals(mission.getShipPointing(), DirectionEnums.SOUTH);

		// Pointing SOUTH
		new Mission(3L, planet, ship, 0, 0, DirectionEnums.SOUTH);
		mission.turn(TurnEnums.RIGHT);
		assertEquals(mission.getShipPointing(), DirectionEnums.WEST);

		// Pointing WEST
		new Mission(3L, planet, ship, 0, 0, DirectionEnums.WEST);
		mission.turn(TurnEnums.RIGHT);
		assertEquals(mission.getShipPointing(), DirectionEnums.NORTH);
	}

}
