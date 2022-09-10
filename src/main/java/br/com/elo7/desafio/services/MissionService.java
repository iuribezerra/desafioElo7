package br.com.elo7.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.desafio.dto.request.CommandsRequest;
import br.com.elo7.desafio.dto.request.MissionRequest;
import br.com.elo7.desafio.entities.Mission;
import br.com.elo7.desafio.entities.Planet;
import br.com.elo7.desafio.entities.Ship;
import br.com.elo7.desafio.enums.TurnEnums;
import br.com.elo7.desafio.exceptions.BusinessException;
import br.com.elo7.desafio.repositories.MissionRepository;

@Service
public class MissionService {

	@Autowired
	MissionRepository missionRepository;

	@Autowired
	PlanetService planetService;

	@Autowired
	ShipService shipService;

	public boolean existsByShip(Ship ship) {
		return missionRepository.existsByShip(ship);
	}

	public Mission findById(Long id) {
		Optional<Mission> mission = missionRepository.findById(id);
		return mission.get();
	}

	public List<Mission> findByPlanet(Long planetId) {
		Planet planet = planetService.findById(planetId);
		return missionRepository.findByPlanet(planet);
	}

	public List<Mission> findAll() {
		return missionRepository.findAll();
	}

	public Mission save(Mission mission) throws Exception {
		return missionRepository.save(mission);
	}

	public Mission save(MissionRequest missionRequest) throws Exception {
		Planet planet = planetService.findById(missionRequest.getPlanetId());
		Ship ship = shipService.findById(missionRequest.getShipId());

		Mission mission = new Mission(missionRequest.getId(), planet, ship, missionRequest.getShipPositionX(),
				missionRequest.getShipPositionY(), missionRequest.getShipPointing());

		List<Mission> missions = findByPlanet(mission.getPlanet().getId());
		// Ship is not used
		if (existsByShip(mission.getShip())) {
			throw new BusinessException("Ship is already on mission");
		}

		// Validate location of X and Y
		canMoveShip(mission, missions);

		return save(mission);
	}

	public Mission moveShip(CommandsRequest commands) throws Exception {
		Mission mission = this.findById(commands.getMissionId());
		List<Mission> missions = findByPlanet(mission.getPlanet().getId());

		for (String move : commands.getMovements()) {
			if (move.equalsIgnoreCase("M")) {
				mission.moveShip();
				canMoveShip(mission, missions);
			} else {
				if (move.equalsIgnoreCase("L")) {
					mission.turn(TurnEnums.LEFT);
				} else {
					mission.turn(TurnEnums.RIGHT);
				}
			}
		}
		// Update ship status after loop
		return this.save(mission);
	}

	public void canMoveShip(Mission mission, List<Mission> missions) throws Exception {
		canMoveIntoPlanet(mission);
		isLocationEmpty(mission, missions);
	}

	public boolean canMoveIntoPlanet(Mission mission) throws Exception {
		int minWidth = mission.getPlanet().getWidth() * -1;
		int maxWidth = mission.getPlanet().getWidth();
		int minHeight = mission.getPlanet().getHeight() * -1;
		int maxHeigh = mission.getPlanet().getHeight();

		if (mission.getShipPositionX() < minWidth || mission.getShipPositionX() > maxWidth) {
			throw new BusinessException("Movement deny, out of planet range. X-AXIS.");
		}

		if (mission.getShipPositionY() < minHeight || mission.getShipPositionY() > maxHeigh) {
			throw new BusinessException("Movement deny, out of planet range. Y-AXIS");
		}

		return true;
	}

	public boolean isLocationEmpty(Mission mission, List<Mission> missions) throws Exception {
		Mission diferentMission = missions.stream()
				.filter(item -> !item.getId().equals(mission.getId())
						&& item.getShipPositionX().equals(mission.getShipPositionX())
						&& item.getShipPositionY().equals(mission.getShipPositionY()))
				.findFirst().orElse(null);

		if (diferentMission != null) {
			throw new BusinessException("Movement deny, blocked way");
		}
		return true;
	}
}
