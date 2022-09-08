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

import br.com.elo7.desafio.dto.request.CommandsRequest;
import br.com.elo7.desafio.dto.request.MissionRequest;
import br.com.elo7.desafio.entities.Mission;
import br.com.elo7.desafio.services.MissionService;

@RestController
@RequestMapping(value = "/missions")
public class MissionResource {

	@Autowired
	private MissionService missionService;

	@PostMapping
	public ResponseEntity<Mission> saveOrUpdate(@RequestBody MissionRequest missionRequest) throws Exception {
		Mission mission = missionService.save(missionRequest);
		return ResponseEntity.ok().body(mission);
	}

	@GetMapping
	public ResponseEntity<List<Mission>> findAll() {
		List<Mission> missions = missionService.findAll();
		return ResponseEntity.ok().body(missions);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Mission> findById(@PathVariable Long id) {
		Mission mission = missionService.findById(id);
		return ResponseEntity.ok().body(mission);
	}

	@PostMapping(value = "/commands")
	public ResponseEntity<Mission> findById(@RequestBody CommandsRequest commands) throws Exception {
		Mission mission = missionService.moveShip(commands);
		return ResponseEntity.ok().body(mission);
	}
}
