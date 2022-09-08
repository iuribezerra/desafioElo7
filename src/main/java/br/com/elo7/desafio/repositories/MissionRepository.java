package br.com.elo7.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elo7.desafio.entities.Mission;
import br.com.elo7.desafio.entities.Planet;
import br.com.elo7.desafio.entities.Ship;

public interface MissionRepository extends JpaRepository<Mission, Long> {
	List<Mission> findByPlanet(Planet planet);
	Boolean existsByShip(Ship ship);
}
