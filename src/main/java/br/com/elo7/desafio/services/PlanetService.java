package br.com.elo7.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.desafio.entities.Planet;
import br.com.elo7.desafio.repositories.PlanetRepository;

@Service
public class PlanetService {

	@Autowired
	PlanetRepository planetRepository;

	public Planet save(Planet planet) {
		return planetRepository.save(planet);
	}

	public Planet findById(Long id) {
		Optional<Planet> planet = planetRepository.findById(id);
		return planet.get();
	}

	public List<Planet> findAll() {
		return planetRepository.findAll();
	}

}
