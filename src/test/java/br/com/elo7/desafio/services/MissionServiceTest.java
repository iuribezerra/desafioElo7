package br.com.elo7.desafio.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.elo7.desafio.dto.request.CommandsRequest;
import br.com.elo7.desafio.dto.request.MissionRequest;
import br.com.elo7.desafio.entities.Mission;
import br.com.elo7.desafio.entities.Planet;
import br.com.elo7.desafio.entities.Ship;
import br.com.elo7.desafio.enums.DirectionEnums;
import br.com.elo7.desafio.exceptions.BusinessException;
import br.com.elo7.desafio.repositories.MissionRepository;
import br.com.elo7.desafio.repositories.PlanetRepository;
import br.com.elo7.desafio.repositories.ShipRepository;

@SpringBootTest
class MissionServiceTest {

	@Autowired
	MissionService missionService;

	@MockBean
	MissionRepository missionRepository;

	@Autowired
	PlanetService planetService;

	@MockBean
	PlanetRepository planetRepository;

	@Autowired
	ShipService shipService;

	@MockBean
	ShipRepository shipRepository;

	@Test
	@DisplayName("Save - Create a new mission")
	void saveTest() throws Exception {
		Ship ship = new Ship(1L, "Voyager 1");
		Planet planet = new Planet(2L, "Marte", 15, 15);

		Mockito.when(planetRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(planet));
		Mockito.when(shipRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ship));

		MissionRequest missionRequest = new MissionRequest(null, 2L, 1L, 1, 2, "NORTH");
		assertDoesNotThrow(() -> missionService.save(missionRequest));
	}

	@Test
	@DisplayName("Save - Ship in mission")
	void saveFailTest() throws Exception {
		Ship ship = new Ship(1L, "Voyager 1");
		Planet planet = new Planet(2L, "Marte", 15, 15);

		Mission mission = new Mission(3L, planet, ship, 3, 3, DirectionEnums.EAST);
		List<Mission> missions = Arrays.asList(mission);

		Mockito.when(planetRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(planet));
		Mockito.when(shipRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ship));
		Mockito.when(missionRepository.findByPlanet(Mockito.any())).thenReturn(missions);
		Mockito.when(missionRepository.existsByShip(Mockito.any())).thenReturn(true);

		MissionRequest missionRequest = new MissionRequest(null, 2L, 1L, 1, 2, "NORTH");
		assertThrows(BusinessException.class, () -> missionService.save(missionRequest));
	}

	@Test
	@DisplayName("Move ship - case 1")
	void moveShipTest() throws Exception {
		Ship ship = new Ship(1L, "Voyager 1");
		Planet planet = new Planet(2L, "Marte", 5, 5);
		Mission mission = new Mission(3L, planet, ship, 1, 2, DirectionEnums.NORTH);
		Mockito.when(missionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mission));
		Mockito.when(planetRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(planet));
		Mockito.when(missionService.save((Mission) Mockito.any())).thenReturn(Mockito.any());

		CommandsRequest commands = new CommandsRequest("LMLMLMLMM", 1L);
		missionService.moveShip(commands);

		assertEquals(mission.getShipPositionX(), 1);
		assertEquals(mission.getShipPositionY(), 3);
		assertEquals(mission.getShipPointing(), DirectionEnums.NORTH);
	}

	@Test
	@DisplayName("Move ship - case 2")
	void moveShipCase2Test() throws Exception {
		Ship ship = new Ship(1L, "Voyager 1");
		Planet planet = new Planet(2L, "Marte", 5, 5);
		Mission mission = new Mission(3L, planet, ship, 3, 3, DirectionEnums.EAST);
		Mockito.when(missionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mission));
		Mockito.when(planetRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(planet));
		Mockito.when(missionService.save((Mission) Mockito.any())).thenReturn(Mockito.any());

		CommandsRequest commands = new CommandsRequest("MMRMMRMRRML", 1L);
		missionService.moveShip(commands);

		assertEquals(mission.getShipPositionX(), 5);
		assertEquals(mission.getShipPositionY(), 1);
		assertEquals(mission.getShipPointing(), DirectionEnums.NORTH);
	}

	@Test
	@DisplayName("Location outsite the planet")
	void canMoveIntoPlanetFailTest() throws Exception {
		// Expected exception for X-Axis
		Ship ship = new Ship(1L, "Voyager 1");
		Planet planet = new Planet(2L, "Marte", 5, 5);
		Mission mission = new Mission(3L, planet, ship, 6, 2, DirectionEnums.NORTH);

		assertThrows(BusinessException.class, () -> {
			this.missionService.canMoveIntoPlanet(mission);
		});

		// Expected exception for Y-Axis
		Ship ship2 = new Ship(4L, "Voyager 2");
		Planet planet2 = new Planet(5L, "Marte", 5, 5);
		Mission mission2 = new Mission(6L, planet2, ship2, 5, -6, DirectionEnums.NORTH);

		assertThrows(BusinessException.class, () -> {
			this.missionService.canMoveIntoPlanet(mission2);
		});
	}

	@Test
	@DisplayName("Location inside the planet")
	void canMoveIntoPlanetTest() throws Exception {
		Ship ship = new Ship(1L, "Voyager 1");
		Ship ship2 = new Ship(2L, "Voyager 2");
		Planet planet = new Planet(3L, "Marte", 5, 5);
		Planet planet2 = new Planet(4L, "Nova marte", 5, 5);
		Mission mission = new Mission(5L, planet, ship, 1, 2, DirectionEnums.NORTH);
		Mission mission2 = new Mission(5L, planet2, ship2, 1, 2, DirectionEnums.NORTH);

		// Same position in different planets
		assertTrue(this.missionService.canMoveIntoPlanet(mission));
		assertTrue(this.missionService.canMoveIntoPlanet(mission2));
	}

	@Test
	@DisplayName("Empty location")
	void isLocationEmptyTest() throws Exception {
		Ship ship = new Ship(1L, "Voyager 1");
		Planet planet = new Planet(2L, "Marte", 5, 5);
		Mission mission = new Mission(3L, planet, ship, 1, 2, DirectionEnums.NORTH);

		List<Mission> missions = Arrays.asList(mission);
		assertTrue(this.missionService.isLocationEmpty(mission, missions));
	}

	@Test
	@DisplayName("Occupied location")
	void isLocationEmptyFailTest() throws Exception {
		Ship ship = new Ship(1L, "Voyager 1");
		Ship ship2 = new Ship(4L, "Voyager 2");
		Planet planet = new Planet(2L, "Marte", 5, 5);
		Mission mission = new Mission(3L, planet, ship, 1, 2, DirectionEnums.NORTH);
		Mission mission2 = new Mission(5L, planet, ship2, 1, 2, DirectionEnums.SOUTH);

		// Expected exception for same position of mission 1
		List<Mission> missions = Arrays.asList(mission, mission2);
		assertThrows(BusinessException.class, () -> {
			this.missionService.isLocationEmpty(mission2, missions);
		});
	}

}
