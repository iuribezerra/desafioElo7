package br.com.elo7.desafio.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.desafio.dto.request.PlanetRequest;
import br.com.elo7.desafio.entities.Planet;
import br.com.elo7.desafio.services.PlanetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/planets")
@Tag(name = "Planets", description = "Endpoints for the planets")
public class PlanetResource {

	@Autowired
	private PlanetService planetService;

	@PostMapping
	@Operation(summary = "Save or Update", description = " <strong>Save:</strong> ID has to be null. <br/> <strong>Update:</strong> Need to has a valid ID.")
	public ResponseEntity<Planet> saveOrUpdate(@Valid @RequestBody PlanetRequest planetRequest) {
		Planet planets = planetService.save(new Planet(planetRequest));
		return ResponseEntity.ok().body(planets);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete", description = "Delete planet by id")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
		planetService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	@Operation(summary = "List planets")
	public ResponseEntity<List<Planet>> findAll() {
		List<Planet> planet = planetService.findAll();
		return ResponseEntity.ok().body(planet);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "List planet by id")
	public ResponseEntity<Planet> findById(@PathVariable Long id) {
		Planet planet = planetService.findById(id);
		return ResponseEntity.ok().body(planet);
	}

}
