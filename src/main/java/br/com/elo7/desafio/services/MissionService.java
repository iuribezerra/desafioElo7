package br.com.elo7.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.desafio.dto.request.Commands;
import br.com.elo7.desafio.entities.Mission;
import br.com.elo7.desafio.enums.TurnEnums;
import br.com.elo7.desafio.repositories.MissionRepository;

@Service
public class MissionService {

	@Autowired
	MissionRepository missionRepository;

	public Mission findById(Long id) {
		Optional<Mission> mission = missionRepository.findById(id);
		return mission.get();
	}

	public List<Mission> findAll() {
		return missionRepository.findAll();
	}

	public Mission save(Mission mission) {
		return missionRepository.save(mission);
	}

	public Mission moveShip(Commands commands) {
		Mission mission = this.findById(commands.getMissionId());

		for (String move : commands.getMovements()) {
			if (move.equalsIgnoreCase("M")) {
				mission.moveShip();
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
}
