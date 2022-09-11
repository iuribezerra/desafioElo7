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

import br.com.elo7.desafio.dto.request.CommandsRequest;
import br.com.elo7.desafio.dto.request.MissionRequest;
import br.com.elo7.desafio.entities.Mission;
import br.com.elo7.desafio.services.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/missions")
@Tag(name = "Missions", description = "Endpoints for the missions")
public class MissionResource {

	@Autowired
	private MissionService missionService;

	@PostMapping
	@Operation(summary = "Save", description = "Missions can only be created."
			+ "<br/>Expected shipPointing: NORTH, SOUTH, EAST or WEST")
	public ResponseEntity<Mission> saveOrUpdate(@Valid @RequestBody MissionRequest missionRequest) throws Exception {
		Mission mission = missionService.save(missionRequest);
		return ResponseEntity.ok().body(mission);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete", description = "Delete mission by id")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
		missionService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	@Operation(summary = "List missions")
	public ResponseEntity<List<Mission>> findAll() {
		List<Mission> missions = missionService.findAll();
		return ResponseEntity.ok().body(missions);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "List mission by id")
	public ResponseEntity<Mission> findById(@PathVariable Long id) {
		Mission mission = missionService.findById(id);
		return ResponseEntity.ok().body(mission);
	}

	@PostMapping(value = "/commands")
	@Operation(summary = "Send commands to the ship into a a especif planet", description = "Allowed commands: "
			+ "<br/> <strong>L</strong> for turn Left" + "<br/> <strong>R</strong> for turn Right"
			+ "<br/> <strong>M</strong> to Move")
	public ResponseEntity<Mission> findById(@Valid @RequestBody CommandsRequest commands) throws Exception {
		Mission mission = missionService.moveShip(commands);
		return ResponseEntity.ok().body(mission);
	}
}
