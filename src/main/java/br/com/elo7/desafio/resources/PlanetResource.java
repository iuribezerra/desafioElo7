package br.com.elo7.desafio.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.desafio.dto.request.PlanetRequest;
import br.com.elo7.desafio.entities.Planet;
import br.com.elo7.desafio.services.PlanetService;

@RestController
@RequestMapping(value = "/planets")
public class PlanetResource {

	@Autowired
	private PlanetService planetService;

	@PostMapping
	public ResponseEntity<Planet> saveOrUpdate(@Valid @RequestBody PlanetRequest planetRequest) {
		Planet planets = planetService.save(new Planet(planetRequest));
		return ResponseEntity.ok().body(planets);
	}

	@GetMapping
	public ResponseEntity<List<Planet>> findAll() {
		List<Planet> planet = planetService.findAll();
		return ResponseEntity.ok().body(planet);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Planet> findById(@PathVariable Long id) {
		Planet planet = planetService.findById(id);
		return ResponseEntity.ok().body(planet);
	}

}
