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

import br.com.elo7.desafio.dto.request.ShipRequest;
import br.com.elo7.desafio.entities.Ship;
import br.com.elo7.desafio.services.ShipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/ships")
@Tag(name = "Ships", description = "Endpoints for the ships")
public class ShipResource {

	@Autowired
	private ShipService shipService;

	@PostMapping
	@Operation(summary = "Save or Update", description = " <strong>Save:</strong> ID has to be null. <br/> <strong>Update:</strong> Need to has a valid ID.")
	public ResponseEntity<Ship> saveOrUpdate(@Valid @RequestBody ShipRequest shipRequest) {
		Ship ships = shipService.save(new Ship(shipRequest));
		return ResponseEntity.ok().body(ships);
	}

	@GetMapping
	@Operation(summary = "List ships")
	public ResponseEntity<List<Ship>> findAll() {
		List<Ship> ships = shipService.findAll();
		return ResponseEntity.ok().body(ships);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "List ship by id")
	public ResponseEntity<Ship> findById(@PathVariable Long id) {
		Ship ship = shipService.findById(id);
		return ResponseEntity.ok().body(ship);
	}

}
