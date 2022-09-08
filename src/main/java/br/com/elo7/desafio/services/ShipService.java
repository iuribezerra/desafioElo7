package br.com.elo7.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.desafio.entities.Ship;
import br.com.elo7.desafio.repositories.ShipRepository;

@Service
public class ShipService {

	@Autowired
	ShipRepository shipRepository;

	public Ship save(Ship ship) {
		return shipRepository.save(ship);
	}

	public Ship findById(Long id) {
		Optional<Ship> ship = shipRepository.findById(id);
		return ship.get();
	}

	public List<Ship> findAll() {
		return shipRepository.findAll();
	}

}
