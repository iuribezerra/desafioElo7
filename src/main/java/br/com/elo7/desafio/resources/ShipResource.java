package br.com.elo7.desafio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.desafio.entities.Ship;
import br.com.elo7.desafio.services.ShipService;

@RestController
@RequestMapping(value = "/ships")
public class ShipResource {

	@Autowired
	private ShipService shipService;

	@PostMapping
	public ResponseEntity<Ship> saveOrUpdate(@RequestBody Ship shipRequest) {
		Ship ships = shipService.save(shipRequest);
		return ResponseEntity.ok().body(ships);
	}

	@GetMapping
	public ResponseEntity<List<Ship>> findAll() {
		List<Ship> ships = shipService.findAll();
		return ResponseEntity.ok().body(ships);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Ship> findById(@PathVariable Long id) {
		Ship ship = shipService.findById(id);
		return ResponseEntity.ok().body(ship);
	}

}
